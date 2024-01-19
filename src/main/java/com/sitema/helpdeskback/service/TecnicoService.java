package com.sitema.helpdeskback.service;

import com.sitema.helpdeskback.domain.Pessoa;
import com.sitema.helpdeskback.domain.Tecnico;
import com.sitema.helpdeskback.domain.dtos.TecnicoDTO;
import com.sitema.helpdeskback.repositories.PessoaRepository;
import com.sitema.helpdeskback.repositories.TecnicoRepository;
import com.sitema.helpdeskback.service.exceptions.DatabaseException;
import com.sitema.helpdeskback.service.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado ! id: " + id));
    }

    public List<Tecnico> findAll() {
        List<Tecnico> obj = tecnicoRepository.findAll();
        return  obj;
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validPorCpfEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO);
        return  tecnicoRepository.save(newObj);
    }

    private void validPorCpfEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
        throw  new DatabaseException("CPF já cadastrado no sistema!");
        }

         obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw  new DatabaseException("E-mail já cadastrado no sistema!");
        }

    }

    public Tecnico update(Integer id,@Valid TecnicoDTO objDto) {
        objDto.setId(id);
        Tecnico oldObj = findById(id);
        validPorCpfEmail(objDto);
        oldObj = new Tecnico(objDto);
        return tecnicoRepository.save(oldObj);
    }

    public void delite(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getChamados().size() > 0){
            throw  new DataIntegrityViolationException("Tecnico possui order de serviço e não pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);
    }
}

package com.sitema.helpdeskback.services;

import com.sitema.helpdeskback.domain.Pessoa;
import com.sitema.helpdeskback.domain.Cliente;
import com.sitema.helpdeskback.domain.dtos.ClienteDTO;
import com.sitema.helpdeskback.repositories.PessoaRepository;
import com.sitema.helpdeskback.repositories.ClienteRepository;
import com.sitema.helpdeskback.services.exceptions.DatabaseException;
import com.sitema.helpdeskback.services.exceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository ClienteRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = ClienteRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado ! id: " + id));
    }

    public List<Cliente> findAll() {
        List<Cliente> obj = ClienteRepository.findAll();
        return  obj;
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validPorCpfEmail(objDTO);
        Cliente newObj = new Cliente(objDTO);
        return  ClienteRepository.save(newObj);
    }

    private void validPorCpfEmail(ClienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
        throw  new DatabaseException("CPF já cadastrado no sistema!");
        }

         obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw  new DatabaseException("E-mail já cadastrado no sistema!");
        }

    }

    public Cliente update(Integer id,@Valid ClienteDTO objDto) {
        objDto.setId(id);

        Cliente oldObj = findById(id);
        validPorCpfEmail(objDto);
        oldObj = new Cliente(objDto);
        return ClienteRepository.save(oldObj);
    }

    public void delite(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() > 0){
            throw  new DataIntegrityViolationException("Cliente possui order de serviço e não pode ser deletado!");
        }
        ClienteRepository.deleteById(id);
    }
}

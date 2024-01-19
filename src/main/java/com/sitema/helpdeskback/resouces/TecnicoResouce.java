package com.sitema.helpdeskback.resouces;

import com.sitema.helpdeskback.domain.Tecnico;
import com.sitema.helpdeskback.domain.dtos.TecnicoDTO;
import com.sitema.helpdeskback.service.TecnicoService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResouce {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico obj = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>>  findAll(){
        List<Tecnico> list = service.findAll();
        List<TecnicoDTO> listDto = list.stream().map(TecnicoDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid  @RequestBody TecnicoDTO objDTO ){
        Tecnico newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDto){
        Tecnico obj =  service.update(id, objDto);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @DeleteMapping(value = "/{ic}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
        service.delite(id);
        return  ResponseEntity.noContent().build();
    }


}

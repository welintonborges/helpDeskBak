package com.sitema.helpdeskback.resouces.exceptions;

import com.sitema.helpdeskback.domain.Cliente;
import com.sitema.helpdeskback.domain.dtos.ClienteDTO;
import com.sitema.helpdeskback.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/Clientes")
public class ClienteResouce {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id){
        Cliente obj = service.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>>  findAll(){
        List<Cliente> list = service.findAll();
        List<ClienteDTO> listDto = list.stream().map(ClienteDTO::new).toList();
        return ResponseEntity.ok().body(listDto);
    }
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid  @RequestBody ClienteDTO objDTO ){
        Cliente newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDto){
        Cliente obj =  service.update(id, objDto);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    @DeleteMapping(value = "/{ic}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        service.delite(id);
        return  ResponseEntity.noContent().build();
    }


}

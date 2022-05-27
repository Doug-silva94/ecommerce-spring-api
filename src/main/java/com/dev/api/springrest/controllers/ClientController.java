package com.dev.api.springrest.controllers;

import com.dev.api.springrest.dtos.ClientDTO;
import com.dev.api.springrest.exceptions.ClientException;
import com.dev.api.springrest.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public ResponseEntity<List<ClientDTO>> listAll(){
        return ResponseEntity.ok(clientService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable long id) throws ClientException {
        return ResponseEntity.ok(clientService.findOneClient(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable long id, @RequestBody ClientDTO clientDTO) throws ClientException {
        clientService.updateClient(id, clientDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<Void> createClient(@RequestBody ClientDTO clientDTO){
        clientService.saveClient(clientDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) throws ClientException {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
}

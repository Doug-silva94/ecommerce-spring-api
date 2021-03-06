package com.dev.api.springrest.controller;

import com.dev.api.springrest.dto.ClientDto;
import com.dev.api.springrest.exception.ClientException;
import com.dev.api.springrest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping()
    public ResponseEntity<Void> createClient(@RequestBody ClientDto clientDto) {
        clientService.saveClient(clientDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable long id) throws ClientException {
        return ResponseEntity.ok(clientService.findOneClient(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateById(@PathVariable long id, @RequestBody ClientDto clientDto) throws ClientException {
        clientService.updateClient(id, clientDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) throws ClientException {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public ResponseEntity<List<ClientDto>> listAll() {
        return ResponseEntity.ok(clientService.listAll());
    }

}

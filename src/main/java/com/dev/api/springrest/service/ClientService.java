package com.dev.api.springrest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.api.springrest.dto.ClientDto;
import com.dev.api.springrest.exception.ClientException;
import com.dev.api.springrest.model.Client;
import com.dev.api.springrest.repository.ClientRepository;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public ClientDto toDto(Client client) {
        ClientDto clientDto = new ClientDto();

        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setUserName(client.getUserName());
        clientDto.setEmail(client.getEmail());
        clientDto.setCpf(client.getCpf());
        clientDto.setBirthDate(client.getBirthDate());
        clientDto.setAddress(client.getAddress());
        clientDto.setTelephone(client.getTelephone());
        return clientDto;
    }
    
    public Client toModel(ClientDto clientDto) {
        Client client = new Client();

        client.setName(clientDto.getName());
        client.setUserName(clientDto.getUserName());
        client.setEmail(clientDto.getEmail());
        client.setCpf(clientDto.getCpf().replace(".", "").replace("-", ""));
        client.setBirthDate(clientDto.getBirthDate());
        client.setAddress(clientDto.getAddress());
        client.setTelephone(clientDto.getTelephone());
        return client;
    }

    public String saveClient(ClientDto clientDto) {
        Client client = toModel(clientDto);
        clientRepository.save(client);
        return "Client " + client.getId() + " successfully saved!";
    }
    
    public ClientDto findOneClient(Long id) throws ClientException {
    	return clientRepository.findById(id)
    			.map(client -> toDto(client))
    			.orElseThrow(() -> new ClientException("Client " + id + " not found. Please, try again!"));
    }

    public String updateClient(Long id, ClientDto clientDto) throws ClientException {
    	Client dataClient = this.clientRepository.findById(id).orElseThrow(() -> 
    	new ClientException("Client " + id + " was not updated. Please, try again."));
    	
    	dataClient.setName(clientDto.getName());
    	dataClient.setEmail(clientDto.getEmail());
    	dataClient.setCpf(clientDto.getCpf());
    	dataClient.setBirthDate(clientDto.getBirthDate());
    	dataClient.setAddress(clientDto.getAddress());
    	dataClient.setTelephone(clientDto.getTelephone());
        clientRepository.save(dataClient);
        return "Client " + id + " successfully updated!";
    }

    public void deleteClient(long id) throws ClientException {
        clientRepository.deleteById(id);
    }

    public List<ClientDto> listAll() {
        return clientRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}

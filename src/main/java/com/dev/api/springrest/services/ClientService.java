package com.dev.api.springrest.services;

import com.dev.api.springrest.dtos.ClientDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.api.springrest.exceptions.ClientException;
import com.dev.api.springrest.exceptions.ClientNotFoundException;
import com.dev.api.springrest.models.Client;
import com.dev.api.springrest.repositories.ClientRepository;


import java.util.stream.Collectors;

@Service
public class ClientService {
   
	@Autowired
    ClientRepository clientRepository;

    public Client dtoToClient(ClientDto clientDto) {
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

    public ClientDto clientToDTO(Client client) {
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

    public void saveClient(ClientDto clientDto) {
        Client client = dtoToClient(clientDto);
        clientRepository.save(client);
    }

    public Client getClientOrElseThrow(Long id) throws ClientNotFoundException {
        return this.clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
    }

    private <T> T getValue(T savedData, String dtoInput) {
        return dtoInput != null ? (T) dtoInput : savedData;
    }

    public ClientDto findOneClient(Long id) throws ClientException {
        var ex = new ClientException(new ClientNotFoundException());
        return clientToDTO(this.getClientOrElseThrow(id));
    }

    public void updateClient(Long id, ClientDto clientDto) throws ClientException {
        Client clientOnBank = this.getClientOrElseThrow(id);
        clientOnBank.setEmail(getValue(clientOnBank.getEmail(), clientDto.getEmail()));
        clientOnBank.setAddress(getValue(clientOnBank.getAddress(), clientDto.getAddress()));
        clientOnBank.setTelephone(getValue(clientOnBank.getTelephone(), clientDto.getTelephone()));
        clientRepository.save(clientOnBank);
    }


    public void deleteClient(long id) throws ClientException {
        clientRepository.deleteById(this.getClientOrElseThrow(id).getId());
    }

    public List<ClientDto> listAll() {
        return clientRepository.findAll()
                .stream()
                .map(this::clientToDTO)
                .collect(Collectors.toList());
    }

}

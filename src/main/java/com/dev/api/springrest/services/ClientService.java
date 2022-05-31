package com.dev.api.springrest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.api.springrest.dtos.ClientDTO;
import com.dev.api.springrest.exceptions.ClientException;
import com.dev.api.springrest.exceptions.ClientNotFoundException;
import com.dev.api.springrest.models.Client;
import com.dev.api.springrest.repositories.ClientRepository;
<<<<<<< HEAD
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
>>>>>>> 6e2ed1bf7eb133be02a242addd9a4243a1aaf825

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public void saveClient(ClientDTO clientDTO) {
        Client client = dtoToClient(clientDTO);
        clientRepository.save(client);
    }

    public ClientDTO clientToDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setUserName(client.getUserName());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setCpf(client.getCpf());
        clientDTO.setBirthDate(client.getBirthDate());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setTelephone(client.getTelephone());

        return clientDTO;
    }

    public Client getClientOrElseThrow(Long id) throws ClientNotFoundException {
        return this.clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
    }

    private <T> T getValue(T savedData, String dtoInput) {
        return dtoInput != null ? (T) dtoInput : savedData;
    }

    public Client dtoToClient(ClientDTO clientDTO) {
        Client client = new Client();

        client.setName(clientDTO.getName());
        client.setUserName(clientDTO.getUserName());
        client.setEmail(clientDTO.getEmail());
        client.setCpf(clientDTO.getCpf().replace(".", "").replace("-", ""));
        client.setBirthDate(clientDTO.getBirthDate());
        client.setAddress(clientDTO.getAddress());
        client.setTelephone(clientDTO.getTelephone());

        return client;
    }

    public ClientDTO findOneClient(Long id) throws ClientException {
        var ex = new ClientException(new ClientNotFoundException());
        return clientToDTO(this.getClientOrElseThrow(id));
    }

    public void updateClient(Long id, ClientDTO clientDTO) throws ClientException {
        Client clientOnBank = this.getClientOrElseThrow(id);

        clientOnBank.setEmail(getValue(clientOnBank.getEmail(), clientDTO.getEmail()));
        clientOnBank.setAddress(getValue(clientOnBank.getAddress(), clientDTO.getAddress()));
        clientOnBank.setTelephone(getValue(clientOnBank.getTelephone(), clientDTO.getTelephone()));
        clientRepository.save(clientOnBank);
    }


    public void deleteClient(long id) throws ClientException {
        clientRepository.deleteById(this.getClientOrElseThrow(id).getId());
    }

    public List<ClientDTO> listAll() {
        return clientRepository.findAll()
                .stream()
                .map(this::clientToDTO)
                .collect(Collectors.toList());
    }

}

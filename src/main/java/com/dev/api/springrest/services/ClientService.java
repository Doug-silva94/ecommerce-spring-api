package com.dev.api.springrest.services;

import com.dev.api.springrest.dtos.ClientDTO;
import com.dev.api.springrest.exceptions.ClientException;
import com.dev.api.springrest.models.Client;
import com.dev.api.springrest.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Client> client = clientRepository.findById(id);
        Client clientOnData;
        ClientDTO clientDTO = new ClientDTO();
        if (client.isPresent()) {
            clientOnData = client.get();
            clientDTO = clientToDTO(client.get());
            return clientDTO;
        }
        throw new ClientException("Id or option invalid.");
    }

    public void updateClient(Long id, ClientDTO clientDTO) throws ClientException {
        Optional<Client> client = clientRepository.findById(id);
        Client clientOnBank = new Client();
        if (client.isPresent()) {
            clientOnBank = client.get();
            if (clientDTO.getUserName() != null) {
                clientOnBank.setUserName(clientDTO.getUserName());
            }
            if (clientDTO.getEmail() != null) {
                clientOnBank.setEmail(clientDTO.getEmail());
            }
            if (clientDTO.getCpf() != null) {
                clientOnBank.setCpf(clientDTO.getCpf());
            }
            if (clientDTO.getBirthDate() != null) {
                clientOnBank.setBirthDate(clientDTO.getBirthDate());
            }
            if (clientDTO.getAddress() != null) {
                clientOnBank.setAddress(clientDTO.getAddress());
            }
            if (clientDTO.getTelephone() != null) {
                clientOnBank.setTelephone(clientDTO.getTelephone());
            }
            clientRepository.save(clientOnBank);
        }
        throw new ClientException("Update not conclude. Specification invalid or not exist.");

    }

    public void deleteClient(long id) throws ClientException {
        Optional<Client> client = clientRepository.findById(id);
        if (id <= 0 || client.isEmpty()) {
            throw new ClientException("Request invalid. Number or client not exists.");
        }
        clientRepository.deleteById(id);
    }

    public List<ClientDTO> listAll() {
        List<Client> client = clientRepository.findAll();
        List<ClientDTO> listClient = new ArrayList<>();
        for (Client clients : client) {
            ClientDTO clientDTO = clientToDTO(clients);
            listClient.add(clientDTO);
        }
        return listClient;
    }

}

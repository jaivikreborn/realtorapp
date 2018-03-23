package com.voidenterprise.realtor.rest.services;

import com.voidenterprise.realtor.models.Client;
import com.voidenterprise.realtor.rest.repositories.ClientRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@NoArgsConstructor
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {

        clientRepository.save(client);
        return client;
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findByEmail(String email, UUID realtorId) {
        return clientRepository.findByEmail(email,realtorId);
    }

    @Override
    public List<Client> getClientsWithBday(Date date, String realtorId) {
        List<Client> clientsResult = new ArrayList<>();
        List<Client> clients = clientRepository.getAllClients(UUID.fromString(realtorId));

        for(Client client : clients){
            Date birthdate = client.getDateOfBirth();
            if(birthdate.getDate() == date.getDate() && birthdate.getMonth() == date.getMonth())
                clientsResult.add(client);
        }
        return clientsResult;
    }
}

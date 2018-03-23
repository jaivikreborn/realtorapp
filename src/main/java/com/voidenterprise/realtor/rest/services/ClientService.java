package com.voidenterprise.realtor.rest.services;

import com.voidenterprise.realtor.models.Client;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ClientService {

    Client addClient(Client client);

    List<Client> getAllClients();

    Client findByEmail(String email, UUID realtorId);

    List<Client> getClientsWithBday(Date date, String realtorId);
}

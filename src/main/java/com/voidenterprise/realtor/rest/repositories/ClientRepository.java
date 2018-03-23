package com.voidenterprise.realtor.rest.repositories;

import com.voidenterprise.realtor.models.Client;
import com.voidenterprise.realtor.models.Realtor;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ClientRepository extends CrudRepository<Client,String> {

    @Query("Select * from clients where id=?0 limit 1")
    Client findById(String id);

    @Query("Select * from clients")
    List<Client> findAll();

    @Query("Select * from clients where email=?0 and realtorid=?1 limit 1 allow filtering")
    Client findByEmail(String email, UUID realtorId);

    @Query("Select * from clients where realtorid=?1 and dateofbirth < ?0 allow filtering")
    List<Client> getClientsWithBday(Date date, UUID uuid);

    @Query("Select * from clients where realtorid=?0")
    List<Client> getAllClients(UUID realtorId);
}

package com.voidenterprise.realtor.rest.repositories;

import com.voidenterprise.realtor.models.Realtor;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RealtorRepository extends CrudRepository<Realtor,String> {

    @Query("Select * from realtor where id=?0 limit 1")
    Realtor findById(String id);

    @Query("Select * from realtor")
    List<Realtor> findAll();

    @Query("Select * from realtor where email=?0 limit 1 allow filtering")
    Realtor findByEmail(String email);
}

package com.voidenterprise.realtor.rest.repositories;

import com.voidenterprise.realtor.models.House;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HouseRepository extends CrudRepository<House,String> {

    @Query("Select * from houses where id=?0 limit 1")
    House findById(String id);

    @Query("Select * from houses")
    List<House> findAll();


}

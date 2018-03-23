package com.voidenterprise.realtor.rest.repositories;

import com.voidenterprise.realtor.models.Restaurant;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface RestaurantRepository extends CrudRepository<Restaurant,String> {

    @Query("Select * from restaurants where id=?0 limit 1")
    Restaurant findById(UUID id);

    @Query("Select * from restaurants")
    List<Restaurant> findAll();


}

package com.voidenterprise.realtor.rest.services;

import com.voidenterprise.realtor.models.Restaurant;
import com.voidenterprise.realtor.rest.repositories.RestaurantRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@NoArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {

        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant getRestaurantById(String id) {
        return restaurantRepository.findById(UUID.fromString(id));
    }
}

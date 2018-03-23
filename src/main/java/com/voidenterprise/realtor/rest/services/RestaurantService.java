package com.voidenterprise.realtor.rest.services;

import com.voidenterprise.realtor.models.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant addRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurantById(String id);

}

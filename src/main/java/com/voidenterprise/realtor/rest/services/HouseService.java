package com.voidenterprise.realtor.rest.services;

import com.voidenterprise.realtor.models.House;
import com.voidenterprise.realtor.models.Realtor;

import java.util.List;

public interface HouseService {

    House addHouse(House realtor);

    List<House> getAllHouses();

}

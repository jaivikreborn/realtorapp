package com.voidenterprise.realtor.rest.services;

import com.voidenterprise.realtor.models.House;
import com.voidenterprise.realtor.models.Realtor;
import com.voidenterprise.realtor.rest.repositories.HouseRepository;
import com.voidenterprise.realtor.rest.repositories.RealtorRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@NoArgsConstructor
public class HouseServiceImpl implements HouseService {

    @Autowired
    HouseRepository houseRepository;

    @Override
    public House addHouse(House house) {

        houseRepository.save(house);
        return house;
    }

    @Override
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

}

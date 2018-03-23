package com.voidenterprise.realtor.rest.services;

import com.voidenterprise.realtor.models.Realtor;

import java.util.List;

public interface RealtorService {

    Realtor addRealtor(Realtor realtor);

    List<Realtor> getAllRealtors();

    Realtor findByEmail(String email);
}

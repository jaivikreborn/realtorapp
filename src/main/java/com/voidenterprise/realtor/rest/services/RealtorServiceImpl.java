package com.voidenterprise.realtor.rest.services;

import com.voidenterprise.realtor.models.Realtor;
import com.voidenterprise.realtor.rest.repositories.RealtorRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@NoArgsConstructor
public class RealtorServiceImpl implements RealtorService {

    @Autowired
    RealtorRepository realtorRepository;

    @Override
    public Realtor addRealtor(Realtor realtor) {

        realtorRepository.save(realtor);
        return realtor;
    }

    @Override
    public List<Realtor> getAllRealtors() {
        return realtorRepository.findAll();
    }

    @Override
    public Realtor findByEmail(String email) {
        return realtorRepository.findByEmail(email);
    }
}

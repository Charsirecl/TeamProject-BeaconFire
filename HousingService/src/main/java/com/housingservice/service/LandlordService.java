package com.housingservice.service;

import com.housingservice.model.Landlord;
import com.housingservice.repository.LandlordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LandlordService {

    @Autowired
    private LandlordRepository landlordRepository;

    public Landlord addLandlord(Landlord landlord) {
        return landlordRepository.save(landlord);
    }

    public List<Landlord> getAllLandlords() {
        return landlordRepository.findAll();
    }

    public Optional<Landlord> getLandlordById(Integer id) {
        return landlordRepository.findById(id);
    }

    public Landlord updateLandlord(Landlord landlord) {
        return landlordRepository.save(landlord);
    }

    public void deleteLandlord(Integer id) {
        landlordRepository.deleteById(id);
    }
}

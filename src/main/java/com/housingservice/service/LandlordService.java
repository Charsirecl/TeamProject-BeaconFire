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

    // Create a new Landlord
    public Landlord addLandlord(Landlord landlord) {
        return landlordRepository.save(landlord);
    }

    // Get all Landlords
    public List<Landlord> getAllLandlords() {
        return landlordRepository.findAll();
    }

    // Get a Landlord by ID
    public Optional<Landlord> getLandlordById(Integer id) {
        return landlordRepository.findById(id);
    }

    // Update an existing Landlord
    public Landlord updateLandlord(Landlord landlord) {
        return landlordRepository.save(landlord);
    }

    // Delete a Landlord
    public void deleteLandlord(Integer id) {
        landlordRepository.deleteById(id);
    }
}

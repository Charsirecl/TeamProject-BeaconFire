package com.housingservice.service;

import com.housingservice.dto.FacilityDTO;
import com.housingservice.model.Facility;
import com.housingservice.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    public Optional<Facility> getFacilityById(int id) {
        return facilityRepository.findById(id);
    }

    public Facility createFacility(FacilityDTO facilityDTO) {
        // Logic to create and save a new Facility
        Facility facility = new Facility();
        // Map fields from facilityDTO to facility
        return facilityRepository.save(facility);
    }

    public Facility updateFacility(int id, FacilityDTO facilityDTO) {
        // Logic to update an existing Facility
        Optional<Facility> facilityOptional = facilityRepository.findById(id);
        if (facilityOptional.isPresent()) {
            Facility facility = facilityOptional.get();
            // Update fields with data from facilityDTO
            return facilityRepository.save(facility);
        }
        return null;
    }

    public void deleteFacility(int id) {
        facilityRepository.deleteById(id);
    }
}
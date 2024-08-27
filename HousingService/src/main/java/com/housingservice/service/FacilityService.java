package com.housingservice.service;

import com.housingservice.model.Facility;
import com.housingservice.model.House;
import com.housingservice.repository.FacilityRepository;
import com.housingservice.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private HouseRepository houseRepository;

    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    public Optional<Facility> getFacilityById(Integer id) {
        return facilityRepository.findById(id);
    }

    public Facility addFacility(Facility facility) {
        // Ensure the House entity is set correctly
        Optional<House> house = houseRepository.findById(facility.getHouse().getId());
        if (house.isPresent()) {
            facility.setHouse(house.get());
        } else {
            throw new IllegalArgumentException("Invalid houseId");
        }
        return facilityRepository.save(facility);
    }

    public Facility updateFacility(Facility facility) {
        // Ensure the House entity is set correctly
        Optional<House> house = houseRepository.findById(facility.getHouse().getId());
        if (house.isPresent()) {
            facility.setHouse(house.get());
        } else {
            throw new IllegalArgumentException("Invalid houseId");
        }

        // Ensure the facilityReports collection is managed properly
        Facility existingFacility = facilityRepository.findById(facility.getId())
                .orElseThrow(() -> new IllegalArgumentException("Facility not found"));

        // Update the facilityReports only if provided; otherwise, retain the existing ones
        if (facility.getFacilityReports() != null) {
            existingFacility.getFacilityReports().clear();
            existingFacility.getFacilityReports().addAll(facility.getFacilityReports());
        }

        // Update other fields
        existingFacility.setType(facility.getType());
        existingFacility.setQuantity(facility.getQuantity());
        existingFacility.setDescription(facility.getDescription());
        existingFacility.setLastModificationDate(LocalDateTime.now());

        return facilityRepository.save(existingFacility);
    }


    public void deleteFacility(Integer id) {
        facilityRepository.deleteById(id);
    }
}

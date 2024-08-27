package com.housingservice.controller;

import com.housingservice.dto.FacilityDTO;
import com.housingservice.model.Facility;
import com.housingservice.service.FacilityService;
import com.housingservice.util.HousingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @Autowired
    private HousingMapper housingMapper;

    // View all facilities
    @GetMapping
    public ResponseEntity<List<FacilityDTO>> getAllFacilities() {
        List<FacilityDTO> facilities = facilityService.getAllFacilities()
                .stream()
                .map(housingMapper::toFacilityDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(facilities);
    }

    // View a specific Facility by ID
    @GetMapping("/{id}")
    public ResponseEntity<FacilityDTO> getFacilityById(@PathVariable Integer id) {
        return facilityService.getFacilityById(id)
                .map(housingMapper::toFacilityDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create a new Facility
    @PostMapping
    public ResponseEntity<FacilityDTO> createFacility(@RequestBody FacilityDTO facilityDTO) {
        Facility facility = housingMapper.toFacilityEntity(facilityDTO);
        Facility createdFacility = facilityService.addFacility(facility);
        return ResponseEntity.ok(housingMapper.toFacilityDTO(createdFacility));
    }

    // Update an existing Facility
    @PutMapping("/{id}")
    public ResponseEntity<FacilityDTO> updateFacility(
            @PathVariable Integer id,
            @RequestBody FacilityDTO facilityDTO) {
        Facility facility = housingMapper.toFacilityEntity(facilityDTO);
        facility.setId(id);
        Facility updatedFacility = facilityService.updateFacility(facility);
        return ResponseEntity.ok(housingMapper.toFacilityDTO(updatedFacility));
    }

    // Delete a Facility
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable Integer id) {
        facilityService.deleteFacility(id);
        return ResponseEntity.noContent().build();
    }
}

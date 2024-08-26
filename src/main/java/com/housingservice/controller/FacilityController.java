package com.housingservice.controller;

import com.housingservice.dto.FacilityDTO;
import com.housingservice.model.Facility;
import com.housingservice.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @GetMapping
    public List<Facility> getAllFacilities() {
        return facilityService.getAllFacilities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facility> getFacilityById(@PathVariable int id) {
        Optional<Facility> facility = facilityService.getFacilityById(id);
        return facility.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Facility createFacility(@RequestBody FacilityDTO facilityDTO) {
        return facilityService.createFacility(facilityDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Facility> updateFacility(@PathVariable int id, @RequestBody FacilityDTO facilityDTO) {
        Facility updatedFacility = facilityService.updateFacility(id, facilityDTO);
        return updatedFacility != null ? ResponseEntity.ok(updatedFacility) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable int id) {
        facilityService.deleteFacility(id);
        return ResponseEntity.noContent().build();
    }
}
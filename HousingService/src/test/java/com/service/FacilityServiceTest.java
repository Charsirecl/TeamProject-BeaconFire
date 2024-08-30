package com.service;

import com.housingservice.model.Facility;
import com.housingservice.model.House;
import com.housingservice.repository.FacilityRepository;
import com.housingservice.repository.HouseRepository;
import com.housingservice.service.FacilityService;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class FacilityServiceTest {

    @InjectMocks
    private FacilityService facilityService;

    @Mock
    private FacilityRepository facilityRepository;

    @Mock
    private HouseRepository houseRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFacilities() {
        Facility facility = new Facility();
        when(facilityRepository.findAll()).thenReturn(Collections.singletonList(facility));

        var result = facilityService.getAllFacilities();
        assertEquals(1, result.size());
        assertEquals(facility, result.get(0));
    }

    @Test
    void testGetFacilityById() {
        Facility facility = new Facility();
        when(facilityRepository.findById(anyInt())).thenReturn(Optional.of(facility));

        var result = facilityService.getFacilityById(1);
        assertTrue(result.isPresent());
        assertEquals(facility, result.get());
    }

    @Test
    void testAddFacility_ValidHouse() {
        House house = new House();
        house.setId(1);
        Facility facility = new Facility();
        facility.setHouse(house);

        when(houseRepository.findById(anyInt())).thenReturn(Optional.of(house));
        when(facilityRepository.save(facility)).thenReturn(facility);

        var result = facilityService.addFacility(facility);
        assertEquals(facility, result);
    }

    @Test
    void testAddFacility_InvalidHouse() {
        Facility facility = new Facility();
        when(houseRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> facilityService.addFacility(facility));
    }

    @Test
    void testUpdateFacility_ValidHouse() {
        House house = new House();
        house.setId(1);
        Facility facility = new Facility();
        facility.setId(1);
        facility.setHouse(house);

        when(houseRepository.findById(anyInt())).thenReturn(Optional.of(house));
        when(facilityRepository.findById(anyInt())).thenReturn(Optional.of(facility));
        when(facilityRepository.save(facility)).thenReturn(facility);

        var result = facilityService.updateFacility(facility);
        assertEquals(facility, result);
    }

    @Test
    void testUpdateFacility_InvalidHouse() {
        Facility facility = new Facility();
        when(houseRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> facilityService.updateFacility(facility));
    }

    @Test
    void testDeleteFacility() {
        doNothing().when(facilityRepository).deleteById(anyInt());

        facilityService.deleteFacility(1);
        verify(facilityRepository, times(1)).deleteById(1);
    }
}
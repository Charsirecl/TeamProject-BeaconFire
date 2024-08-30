package com.service;

import com.housingservice.model.Landlord;
import com.housingservice.repository.LandlordRepository;
import com.housingservice.service.LandlordService;
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

class LandlordServiceTest {

    @InjectMocks
    private LandlordService landlordService;

    @Mock
    private LandlordRepository landlordRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllLandlords() {
        Landlord landlord = new Landlord();
        when(landlordRepository.findAll()).thenReturn(Collections.singletonList(landlord));

        var result = landlordService.getAllLandlords();
        assertEquals(1, result.size());
        assertEquals(landlord, result.get(0));
    }

    @Test
    void testGetLandlordById() {
        Landlord landlord = new Landlord();
        when(landlordRepository.findById(anyInt())).thenReturn(Optional.of(landlord));

        var result = landlordService.getLandlordById(1);
        assertTrue(result.isPresent());
        assertEquals(landlord, result.get());
    }

    @Test
    void testAddLandlord() {
        Landlord landlord = new Landlord();
        when(landlordRepository.save(landlord)).thenReturn(landlord);

        var result = landlordService.addLandlord(landlord);
        assertEquals(landlord, result);
    }

    @Test
    void testUpdateLandlord() {
        Landlord landlord = new Landlord();
        when(landlordRepository.save(landlord)).thenReturn(landlord);

        var result = landlordService.updateLandlord(landlord);
        assertEquals(landlord, result);
    }

    @Test
    void testDeleteLandlord() {
        doNothing().when(landlordRepository).deleteById(anyInt());

        landlordService.deleteLandlord(1);
        verify(landlordRepository, times(1)).deleteById(1);
    }
}
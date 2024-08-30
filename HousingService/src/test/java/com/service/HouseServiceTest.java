package com.service;

import com.housingservice.model.House;
import com.housingservice.model.Landlord;
import com.housingservice.repository.HouseRepository;
import com.housingservice.repository.LandlordRepository;
import com.housingservice.service.HouseService;
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

class HouseServiceTest {

    @InjectMocks
    private HouseService houseService;

    @Mock
    private HouseRepository houseRepository;

    @Mock
    private LandlordRepository landlordRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllHouses() {
        House house = new House();
        when(houseRepository.findAll()).thenReturn(Collections.singletonList(house));

        var result = houseService.getAllHouses();
        assertEquals(1, result.size());
        assertEquals(house, result.get(0));
    }

    @Test
    void testGetHouseById() {
        House house = new House();
        when(houseRepository.findById(anyInt())).thenReturn(Optional.of(house));

        var result = houseService.getHouseById(1);
        assertTrue(result.isPresent());
        assertEquals(house, result.get());
    }

    @Test
    void testAddHouse_ValidLandlord() {
        Landlord landlord = new Landlord();
        landlord.setId(1);
        House house = new House();
        house.setLandlord(landlord);

        when(landlordRepository.findById(anyInt())).thenReturn(Optional.of(landlord));
        when(houseRepository.save(house)).thenReturn(house);

        var result = houseService.addHouse(house);
        assertEquals(house, result);
    }

    @Test
    void testAddHouse_InvalidLandlord() {
        House house = new House();
        when(landlordRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> houseService.addHouse(house));
    }

    @Test
    void testUpdateHouse_ValidLandlord() {
        Landlord landlord = new Landlord();
        landlord.setId(1);
        House house = new House();
        house.setId(1);
        house.setLandlord(landlord);

        when(landlordRepository.findById(anyInt())).thenReturn(Optional.of(landlord));
        when(houseRepository.findById(anyInt())).thenReturn(Optional.of(house));
        when(houseRepository.save(house)).thenReturn(house);

        var result = houseService.updateHouse(house);
        assertEquals(house, result);
    }

    @Test
    void testUpdateHouse_InvalidLandlord() {
        House house = new House();
        when(landlordRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> houseService.updateHouse(house));
    }

    @Test
    void testDeleteHouse() {
        doNothing().when(houseRepository).deleteById(anyInt());

        houseService.deleteHouse(1);
        verify(houseRepository, times(1)).deleteById(1);
    }
}
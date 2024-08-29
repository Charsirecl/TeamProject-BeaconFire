package com.housingservice.service;

import com.housingservice.client.EmployeeClient;
import com.housingservice.model.House;
import com.housingservice.model.Landlord;
import com.housingservice.repository.HouseRepository;
import com.housingservice.repository.LandlordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private LandlordRepository landlordRepository;

    @Autowired
    private EmployeeClient employeeClient;

    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    public Optional<House> getHouseById(Integer id) {
        return houseRepository.findById(id);
    }

    public House addHouse(House house) {
        if (house.getLandlord() == null || house.getLandlord().getId() == null) {
            throw new IllegalArgumentException("Landlord ID is missing");
        }

        Optional<Landlord> landlord = landlordRepository.findById(house.getLandlord().getId());
        if (landlord.isPresent()) {
            house.setLandlord(landlord.get());
        } else {
            throw new IllegalArgumentException("Invalid landlordId");
        }
        return houseRepository.save(house);
    }

    public House updateHouse(House house) {
        if (house.getLandlord() == null || house.getLandlord().getId() == null) {
            throw new IllegalArgumentException("Landlord ID is missing");
        }

        Optional<Landlord> landlord = landlordRepository.findById(house.getLandlord().getId());
        if (landlord.isPresent()) {
            house.setLandlord(landlord.get());
        } else {
            throw new IllegalArgumentException("Invalid landlordId");
        }
        return houseRepository.save(house);
    }

    public void deleteHouse(Integer id) {
        houseRepository.deleteById(id);
    }

}

package com.housingservice.service;

import com.housingservice.dto.HouseDTO;
import com.housingservice.model.House;
import com.housingservice.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    @Autowired
    private HouseRepository houseRepository;

    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    public Optional<House> getHouseById(int id) {
        return houseRepository.findById(id);
    }

    public House createHouse(HouseDTO houseDTO) {
        // Logic to create and save a new House
        House house = new House();
        // Map fields from houseDTO to house
        return houseRepository.save(house);
    }

    public House updateHouse(int id, HouseDTO houseDTO) {
        // Logic to update an existing House
        Optional<House> houseOptional = houseRepository.findById(id);
        if (houseOptional.isPresent()) {
            House house = houseOptional.get();
            // Update fields with data from houseDTO
            return houseRepository.save(house);
        }
        return null;
    }

    public void deleteHouse(int id) {
        houseRepository.deleteById(id);
    }
}

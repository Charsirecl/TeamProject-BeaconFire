package com.housingservice.controller;

import com.housingservice.dto.HouseDTO;
import com.housingservice.model.House;
import com.housingservice.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/houses")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @GetMapping
    public List<House> getAllHouses() {
        return houseService.getAllHouses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<House> getHouseById(@PathVariable int id) {
        Optional<House> house = houseService.getHouseById(id);
        return house.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public House createHouse(@RequestBody HouseDTO houseDTO) {
        return houseService.createHouse(houseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable int id, @RequestBody HouseDTO houseDTO) {
        House updatedHouse = houseService.updateHouse(id, houseDTO);
        return updatedHouse != null ? ResponseEntity.ok(updatedHouse) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouse(@PathVariable int id) {
        houseService.deleteHouse(id);
        return ResponseEntity.noContent().build();
    }
}

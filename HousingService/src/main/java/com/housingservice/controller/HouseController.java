
package com.housingservice.controller;

import com.housingservice.dto.HouseDTO;
import com.housingservice.model.House;
import com.housingservice.service.CompositeService;
import com.housingservice.service.HouseService;
import com.housingservice.util.HousingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/houses")
public class HouseController {

    @Autowired
    private HouseService houseService;


    @Autowired
    private CompositeService compositeService;

    @Autowired
    private HousingMapper housingMapper;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getHouseDetails(@PathVariable Integer id) {
        Map<String, Object> houseWithEmployeeData = compositeService.getHouseWithEmployeeData(id);
        return ResponseEntity.ok(houseWithEmployeeData);
    }

    @PostMapping
    public ResponseEntity<HouseDTO> createHouse(@RequestBody HouseDTO houseDTO) {
        House house = housingMapper.toHouseEntity(houseDTO);
        House createdHouse = houseService.addHouse(house);
        HouseDTO createdHouseDTO = housingMapper.toHouseDTO(createdHouse);
        return ResponseEntity.ok(createdHouseDTO);
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllHouses() {
        List<Map<String, Object>> allHousesWithEmployeeData = compositeService.getAllHousesWithEmployeeData();
        return ResponseEntity.ok(allHousesWithEmployeeData);
    }
}

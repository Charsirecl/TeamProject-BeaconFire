//package com.housingservice.controller;
//
//import com.housingservice.model.House;
//import com.housingservice.service.HouseService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/houses")
//public class HouseController {
//
//    @Autowired
//    private HouseService houseService;
//
//    // View assigned housing details
//    @GetMapping("/{id}")
//    public ResponseEntity<House> getHouseDetails(@PathVariable Integer id) {
//        return houseService.getHouseById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Users are unable to modify housing details, so no POST/PUT/DELETE methods for House
//
//    @PostMapping
//    public ResponseEntity<House> createHouse(@RequestBody House house) {
//        House createdHouse = houseService.addHouse(house);
//        return ResponseEntity.ok(createdHouse);
//    }
//}

package com.housingservice.controller;

import com.housingservice.dto.HouseDTO;
import com.housingservice.model.House;
import com.housingservice.service.HouseService;
import com.housingservice.util.HousingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/houses")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private HousingMapper housingMapper;

    // View assigned housing details
    @GetMapping("/{id}")
    public ResponseEntity<HouseDTO> getHouseDetails(@PathVariable Integer id) {
        return houseService.getHouseById(id)
                .map(housingMapper::toHouseDTO) // Map to DTO before returning
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Users are unable to modify housing details, so no POST/PUT/DELETE methods for House

    @PostMapping
    public ResponseEntity<HouseDTO> createHouse(@RequestBody HouseDTO houseDTO) {
        House house = housingMapper.toHouseEntity(houseDTO);
        House createdHouse = houseService.addHouse(house);
        HouseDTO createdHouseDTO = housingMapper.toHouseDTO(createdHouse);
        return ResponseEntity.ok(createdHouseDTO);
    }

}

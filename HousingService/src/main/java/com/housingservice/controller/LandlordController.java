package com.housingservice.controller;

import com.housingservice.model.Landlord;
import com.housingservice.service.LandlordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/landlords")
public class LandlordController {

    @Autowired
    private LandlordService landlordService;

    @PostMapping
    public ResponseEntity<Landlord> createLandlord(@RequestBody Landlord landlord) {
        Landlord createdLandlord = landlordService.addLandlord(landlord);
        return ResponseEntity.ok(createdLandlord);
    }

}

package com.composite.visastatuscompositeservice.controller;

import com.composite.visastatuscompositeservice.service.VisaStatusCompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/composite")
public class VisaStatusController {
    @Autowired
    private VisaStatusCompositeService visaStatusCompositeService;

    @PostMapping("/updateEmployeeDocument")
    public ResponseEntity<String> updateEmployeeDocument(@RequestParam("employeeId") int employeeId,
                                                         @RequestParam("file") MultipartFile file,
                                                         @RequestParam("documentType") String documentType,
                                                         @RequestParam("description") String description,
                                                         @RequestParam("isRequired") boolean isRequired) {
        visaStatusCompositeService.updateEmployeeDocument(employeeId, file, documentType, description, isRequired);
        return ResponseEntity.ok("Application updated successfully!");
    }

}

package com.example.employeeservice.controller;

import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.domain.PersonalDocument;
import com.example.employeeservice.domain.VisaStatus;
import com.example.employeeservice.service.EmployeeService;
import com.example.employeeservice.service.VisaStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class VisaStatusController {
    @Autowired
    private VisaStatusService visaStatusServiceService;

    @PutMapping("/visaStatus/{employeeId}")
    public ResponseEntity<Employee> updateVisaStatus(@PathVariable String employeeId,
                                                     @RequestBody VisaStatus visaStatus) {
        Employee updatedEmployee = visaStatusServiceService.updateVisaStatus(employeeId, visaStatus);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/personalDocument/{employeeId}")
    public ResponseEntity<Employee> updatePersonalDocument(@PathVariable String employeeId,
                                                           @RequestBody PersonalDocument document) {
        Employee updatedEmployee = visaStatusServiceService.updatePersonalDocument(employeeId, document);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

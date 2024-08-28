package com.example.employeeservice.controller;

import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
>>>>>>> Stashed changes

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
<<<<<<< Updated upstream
=======

    @PostMapping("/createOrUpdate")
    public ResponseEntity<Employee> createOrUpdateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.createOrUpdateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
>>>>>>> Stashed changes
}


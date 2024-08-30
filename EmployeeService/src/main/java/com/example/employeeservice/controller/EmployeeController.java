package com.example.employeeservice.controller;

import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.domain.PersonalDocument;
import com.example.employeeservice.domain.VisaStatus;
import com.example.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable String employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/createOrUpdate")
    public ResponseEntity<Employee> createOrUpdateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.createOrUpdateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }


}


package com.example.employeeservice.controller;

import com.example.employeeservice.domain.*;
import com.example.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/custom/{employeeId}")
    public Employee getEmployeeByIdCustom(@PathVariable String employeeId) {
        return employeeService.getEmployeeByIdCustom(employeeId);
    }

    @PostMapping("/createOrUpdate")
    public ResponseEntity<Employee> createOrUpdateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.createOrUpdateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("/house/{houseId}")
    public List<Map<String, Object>> getEmployeeIdsByHouseId(@PathVariable Integer houseId) {
        String houseIdStr = houseId.toString();
        return employeeService.getEmployeesByHouseId(houseIdStr);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }
    @GetMapping("/profiles")
    public ResponseEntity<Page<EmployeeProfileSummary>> getEmployeeProfiles(Pageable pageable) {
        Page<EmployeeProfileSummary> employees = employeeService.getEmployeeProfiles(pageable);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<EmployeeProfileSummary>> searchEmployees(@RequestParam String query, Pageable pageable) {
        Page<EmployeeProfileSummary> employees = employeeService.searchEmployees(query, pageable);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/visa-status")
    public ResponseEntity<Page<EmployeeVisaStatusResponse>> getActiveVisaEmployees(Pageable pageable) {
        Page<EmployeeVisaStatusResponse> visaStatuses = employeeService.getActiveVisaEmployees(pageable);
        return ResponseEntity.ok(visaStatuses);
    }

    @GetMapping("/profile/{employeeId}")
    public ResponseEntity<EmployeeProfileSummary> getEmployeeProfileById(@PathVariable String employeeId) {
        EmployeeProfileSummary employeeProfile = employeeService.getEmployeeProfileById(employeeId);
        if (employeeProfile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeProfile);
    }
}


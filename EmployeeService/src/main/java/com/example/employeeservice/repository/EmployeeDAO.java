package com.example.employeeservice.repository;

import com.example.employeeservice.domain.Employee;

import java.util.Optional;

public interface EmployeeDAO {
    Optional<Employee> findByCustomId(String id);
}

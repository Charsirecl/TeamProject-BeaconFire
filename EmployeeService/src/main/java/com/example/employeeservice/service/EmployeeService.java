package com.example.employeeservice.service;

import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
<<<<<<< Updated upstream
=======
import java.util.Optional;
>>>>>>> Stashed changes

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
<<<<<<< Updated upstream
=======

    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createOrUpdateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findByUserID(employee.getUserID());

        if (existingEmployee.isPresent()) {
            Employee updatedEmployee = existingEmployee.get();
            updatedEmployee.setFirstName(employee.getFirstName());
            updatedEmployee.setLastName(employee.getLastName());
            updatedEmployee.setMiddleName(employee.getMiddleName());
            updatedEmployee.setPreferedName(employee.getPreferedName());
            updatedEmployee.setEmail(employee.getEmail());
            updatedEmployee.setCellPhone(employee.getCellPhone());
            updatedEmployee.setAlternatePhone(employee.getAlternatePhone());
            updatedEmployee.setGender(employee.getGender());
            updatedEmployee.setSsn(employee.getSsn());
            updatedEmployee.setDob(employee.getDob());
            updatedEmployee.setStartDate(employee.getStartDate());
            updatedEmployee.setEndDate(employee.getEndDate());
            updatedEmployee.setDriverLicense(employee.getDriverLicense());
            updatedEmployee.setDriverLicenseExpiration(employee.getDriverLicenseExpiration());
            updatedEmployee.setHouseID(employee.getHouseID());
            updatedEmployee.setContact(employee.getContact());
            updatedEmployee.setAddress(employee.getAddress());
            updatedEmployee.setVisaStatus(employee.getVisaStatus());
            updatedEmployee.setPersonalDocument(employee.getPersonalDocument());
            return employeeRepository.save(updatedEmployee);
        } else {
            employee.setId(null); // Generate new Employee ID if needed
            return employeeRepository.save(employee);
        }
    }
>>>>>>> Stashed changes
}


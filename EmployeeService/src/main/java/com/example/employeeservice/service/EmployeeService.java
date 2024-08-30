package com.example.employeeservice.service;

import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.domain.EmployeeProfileSummary;
import com.example.employeeservice.domain.EmployeeVisaStatusResponse;
import com.example.employeeservice.domain.VisaStatus;
import com.example.employeeservice.exception.ResourceNotFoundException;
import com.example.employeeservice.repository.EmployeeDAO;
import com.example.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDAO employeeDAO;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByIdCustom(String id) {
        return employeeDAO.findByCustomId(id).orElse(null);
    }

    public Employee getEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
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


    public Page<Employee> getEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Page<EmployeeVisaStatusResponse> getActiveVisaEmployees(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findByVisaStatusActiveFlagTrue(pageable);
        return employees.map(employee -> {
            VisaStatus activeVisa = employee.getVisaStatus().stream()
                    .filter(VisaStatus::getActiveFlag)
                    .findFirst()
                    .orElse(null);

            if (activeVisa != null) {
                LocalDate endDate = LocalDate.parse(activeVisa.getEndDate());
                long daysLeft = LocalDate.now().until(endDate).getDays();
                return new EmployeeVisaStatusResponse(
                        employee.getLegalFullName(),
                        activeVisa.getVisaType(),
                        activeVisa.getEndDate(),
                        daysLeft
                );
            } else {
                return null;
            }
        });
    }

    public Page<EmployeeProfileSummary> getEmployeeProfiles(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAllByOrderByLastNameAsc(pageable);
        return employees.map(employee -> {
            // Get the active visa status title (if any)
            String workAuthorizationTitle = employee.getVisaStatus().stream()
                    .filter(VisaStatus::getActiveFlag)
                    .map(VisaStatus::getVisaType)
                    .findFirst()
                    .orElse("N/A");

            return new EmployeeProfileSummary(
                    employee.getLegalFullName(),
                    employee.getSsn(),
                    workAuthorizationTitle,
                    employee.getCellPhone(),
                    employee.getEmail()
            );
        });
    }

    public Page<EmployeeProfileSummary> searchEmployees(String query, Pageable pageable) {
        List<String> nameParts = Arrays.asList(query.split("\\s+"));

        String firstName = nameParts.size() > 0 ? nameParts.get(0) : "";
        String lastName = nameParts.size() > 1 ? nameParts.get(nameParts.size() - 1) : "";
        String middleName = nameParts.size() > 2 ? String.join(" ", nameParts.subList(1, nameParts.size() - 1)) : "";

        Page<Employee> employees;

        if (!middleName.isEmpty()) {
            employees = employeeRepository.findByFirstNameContainingIgnoreCaseAndMiddleNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
                    firstName, middleName, lastName, pageable);
        } else if (!lastName.isEmpty()) {
            employees = employeeRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
                    firstName, lastName, pageable);
        } else {
            employees = employeeRepository.findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                    firstName, firstName, pageable);
        }

        return employees.map(employee -> {
            String workAuthorizationTitle = employee.getVisaStatus().stream()
                    .filter(visa -> visa.getActiveFlag() != null && visa.getActiveFlag())
                    .map(VisaStatus::getVisaType)
                    .findFirst()
                    .orElse("N/A");

            return new EmployeeProfileSummary(
                    employee.getLegalFullName(),
                    employee.getSsn(),
                    workAuthorizationTitle,
                    employee.getCellPhone(),
                    employee.getEmail()
            );
        });
    }

     public EmployeeProfileSummary getEmployeeProfileById(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        String workAuthorizationTitle = employee.getVisaStatus().stream()
                .filter(VisaStatus::getActiveFlag)
                .map(VisaStatus::getVisaType)
                .findFirst()
                .orElse("N/A");

        return new EmployeeProfileSummary(
                employee.getLegalFullName(),
                employee.getSsn(),
                workAuthorizationTitle,
                employee.getCellPhone(),
                employee.getEmail()
        );
    }
    
    public Map<String, Object> getEmployeeDetails(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        Map<String, Object> employeeMap = new HashMap<>();
        employeeMap.put("id", employee.getObjectId().toHexString());
        employeeMap.put("firstName", employee.getFirstName());
        employeeMap.put("lastName", employee.getLastName());
        employeeMap.put("email", employee.getEmail());
        employeeMap.put("phone", employee.getCellPhone());
        return employeeMap;
    }

    public List<Map<String, Object>> getEmployeesByHouseId(String houseId) {
        List<Employee> employees = employeeRepository.findByHouseID(houseId.toString());
        return employees.stream()
                .map(employee -> {
                    Map<String, Object> employeeMap = new HashMap<>();
                    employeeMap.put("id", employee.getObjectId().toHexString());
                    employeeMap.put("firstName", employee.getFirstName());
                    employeeMap.put("lastName", employee.getLastName());
                    employeeMap.put("email", employee.getEmail());
                    employeeMap.put("phone", employee.getCellPhone());
                    return employeeMap;
                })
                .collect(Collectors.toList());
    }
}


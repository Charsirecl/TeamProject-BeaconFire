package com.example.employeeservice.repository;

import com.example.employeeservice.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    // employeeId
    Optional<Employee> findById(String id);

    Optional<Employee> findByUserID(String userID);

    Page<Employee> findByVisaStatusActiveFlagTrue(Pageable pageable);

    Page<Employee> findByLastNameContainingOrEmailContainingAllIgnoreCase(String lastName, String email, Pageable pageable);

    Page<Employee> findAllByOrderByLastNameAsc(Pageable pageable);

    Page<Employee> findByFirstNameContainingIgnoreCaseAndMiddleNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
            String firstName, String middleName, String lastName, Pageable pageable);

    Page<Employee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
            String firstName, String lastName, Pageable pageable);

    Page<Employee> findByFirstNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String firstName, String email, Pageable pageable);

    List<Employee> findByHouseID(String houseId);
}

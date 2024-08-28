package com.example.employeeservice.repository;

import com.example.employeeservice.domain.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

<<<<<<< Updated upstream
public interface EmployeeRepository extends MongoRepository<Employee, String> {
=======
import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    // employeeId
    Optional<Employee> findById(String id);

    Optional<Employee> findByUserID(String userID);
>>>>>>> Stashed changes
}

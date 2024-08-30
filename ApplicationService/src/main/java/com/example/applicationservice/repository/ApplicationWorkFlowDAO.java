package com.example.applicationservice.repository;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplicationWorkFlowDAO {
    Optional<ApplicationWorkFlow> findById(int id);
    Optional<List<ApplicationWorkFlow>> findByEmployeeID(int employeeId);
    List<ApplicationWorkFlow> findAll();
    ApplicationWorkFlow save(ApplicationWorkFlow applicationWorkFlow);
    void deleteById(int id);
    List<ApplicationWorkFlow> findByStatus(String status);
}

package com.example.employeeservice.repository;

import com.example.employeeservice.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Optional;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Optional<Employee> findByCustomId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Employee employee = mongoTemplate.findOne(query, Employee.class);
        return Optional.ofNullable(employee);
    }
}


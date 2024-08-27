package com.housingservice.service;

import com.housingservice.dto.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmployeeService {

    @Autowired
    private RestTemplate restTemplate;

    public EmployeeDTO getEmployeeById(Integer id) {
        String url = "http://employee-service/api/employees/" + id;
        return restTemplate.getForObject(url, EmployeeDTO.class);
    }
}

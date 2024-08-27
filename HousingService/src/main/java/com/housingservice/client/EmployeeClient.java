package com.housingservice.client;

import com.housingservice.dto.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "employee-service")
public interface EmployeeClient {

    @GetMapping("/api/employees/house/{houseId}")
    List<Integer> getEmployeeIdsByHouseId(@PathVariable("houseId") Integer houseId);

    @GetMapping("/api/employees/{id}")
    EmployeeDTO getEmployeeById(@PathVariable("id") Integer id);
}

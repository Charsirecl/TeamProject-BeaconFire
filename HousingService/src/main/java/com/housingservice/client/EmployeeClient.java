package com.housingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "EMPLOYEESERVICE")
public interface EmployeeClient {

    @GetMapping("/api/employees/house/{houseId}")
    List<Map<String, Object>> getEmployeesByHouseId(@PathVariable("houseId") Integer houseId);

    @GetMapping("/api/employees/{id}")
    Map<String, Object> getEmployeeById(@PathVariable("id") String employeeId);
}

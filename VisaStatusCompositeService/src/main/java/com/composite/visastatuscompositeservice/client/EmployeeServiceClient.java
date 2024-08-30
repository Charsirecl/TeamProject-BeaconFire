package com.composite.visastatuscompositeservice.client;

import com.composite.visastatuscompositeservice.dto.EmployeeService.PersonalDocument;
import com.composite.visastatuscompositeservice.dto.EmployeeService.VisaStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "employee-service", url = "http://localhost:8080") // Replace with your EmployeeService URL
public interface EmployeeServiceClient {

    @PutMapping("/api/employees/personalDocument/{employeeId}")
    ResponseEntity<Void> updatePersonalDocument(@PathVariable("employeeId") String employeeId,
                                                @RequestBody PersonalDocument document);

    @PutMapping("/api/employees/visaStatus/{employeeId}")
    ResponseEntity<Void> updateVisaStatus(@PathVariable("employeeId") String employeeId,
                                          @RequestBody VisaStatus visaStatus);
}



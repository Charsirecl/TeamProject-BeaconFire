package com.composite.visastatuscompositeservice.client;

import com.composite.visastatuscompositeservice.dto.ApplicationService.ApplicationWorkFlow;
//import com.example.shared.models.ApplicationWorkFlow;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ApplicationService", url = "http://localhost:8081")
public interface ApplicationWorkFlowServiceClient {

//    @PostMapping("/api/onboarding")
//    ResponseEntity<ApplicationWorkFlow> submitApplication(
//            @RequestBody ApplicationWorkFlow applicationWorkFlow);

    @PostMapping("ApplicationService/api/submit")
    ResponseEntity<ApplicationWorkFlow> submitApplication(
            @RequestParam int employeeID,
            @RequestParam String status,
            @RequestParam(required = false) String comment,
            @RequestHeader(value = "Authorization") String authorization);

    @GetMapping("ApplicationService/api/status/{employeeId}")
    ResponseEntity<List<ApplicationWorkFlow>> getApplicationsByEmployeeId(
            @PathVariable("employeeId") int employeeId,
            @RequestHeader(value = "Authorization") String authorization);

}


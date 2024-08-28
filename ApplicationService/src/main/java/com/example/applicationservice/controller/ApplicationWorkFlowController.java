package com.example.applicationservice.controller;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import com.example.applicationservice.service.ApplicationWorkFlowService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationWorkFlowController {

    @Autowired
    private ApplicationWorkFlowService applicationWorkFlowService;

    @GetMapping("/application-workflows")
    public List<ApplicationWorkFlow> getAllApplicationWorkFlows() {
        return applicationWorkFlowService.getAllApplicationWorkFlows();
    }

//    test output
//    @GetMapping
//    public ResponseEntity<String> getAllApplicationWorkFlows() throws JsonProcessingException {
//        List<ApplicationWorkFlow> workflows = applicationWorkFlowService.getAllApplicationWorkFlows();
//        return ResponseEntity.ok().body(new ObjectMapper().writeValueAsString(workflows));
//    }

    @GetMapping("/onboarding/{employeeId}")
    public ResponseEntity<String> getOnboardingStatus(@PathVariable int employeeId) {
        String statusMessage = applicationWorkFlowService.checkOnboardingStatus(employeeId);
        if (statusMessage.equals("Application approved. Redirecting to home page.")) {
            return ResponseEntity.ok(statusMessage);
        } else if (statusMessage.equals("Never submitted")) {
            return ResponseEntity.status(400).body(statusMessage);
        } else {
            return ResponseEntity.status(200).body(statusMessage);
        }
    }

    @PostMapping("/onboarding")
    public ResponseEntity<ApplicationWorkFlow> submitApplication(@RequestBody ApplicationWorkFlow applicationWorkFlow) {
        ApplicationWorkFlow savedApplication = applicationWorkFlowService.saveApplication(applicationWorkFlow);
        return ResponseEntity.ok(savedApplication);
    }
}

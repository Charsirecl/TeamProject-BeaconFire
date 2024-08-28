package com.example.applicationservice.service;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import com.example.applicationservice.repository.ApplicationWorkFlowDAO;
import com.example.applicationservice.repository.ApplicationWorkFlowDAOImpl;
//import com.example.applicationservice.repository.ApplicationWorkFlowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationWorkFlowService {

//    @Autowired
//    private ApplicationWorkFlowRepository applicationWorkFlowRepository;
//
//    public List<ApplicationWorkFlow> getAllApplicationWorkFlows() {
//        return applicationWorkFlowRepository.findAll();
//    }

    private final ApplicationWorkFlowDAO applicationWorkFlowDAO;

    @Autowired
    public ApplicationWorkFlowService(ApplicationWorkFlowDAOImpl applicationWorkFlowDAO) {
        this.applicationWorkFlowDAO = applicationWorkFlowDAO;
    }

    @Transactional(readOnly = true)
    public List<ApplicationWorkFlow> getAllApplicationWorkFlows() {
        return applicationWorkFlowDAO.findAll();
    }

    public ApplicationWorkFlow getApplicationStatus(int employeeId) {
        return applicationWorkFlowDAO.findByEmployeeID(employeeId).orElse(null);
    }

    public ApplicationWorkFlow saveApplication(ApplicationWorkFlow applicationWorkFlow) {
        return applicationWorkFlowDAO.save(applicationWorkFlow);
    }

    public String checkOnboardingStatus(int employeeId) {
        Optional<ApplicationWorkFlow> application = applicationWorkFlowDAO.findByEmployeeID(employeeId);

        if (application.isEmpty()) {
            return "Never submitted";
        }

        switch (application.get().getStatus()) {
            case "Pending":
                return "Please wait for HR to review your application.";
            case "Rejected":
                return "Your application was rejected. Please review the feedback and resubmit.";
            case "Approved":
                return "Application approved. Redirecting to home page.";
            default:
                return "Unknown application status.";
        }
    }
}

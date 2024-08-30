package com.example.applicationservice.service;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import com.example.applicationservice.repository.ApplicationWorkFlowDAO;
import com.example.applicationservice.repository.ApplicationWorkFlowDAOImpl;
//import com.example.applicationservice.repository.ApplicationWorkFlowRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationWorkFlowService {

    private final ApplicationWorkFlowDAO applicationWorkFlowDAO;

    @Autowired
    public ApplicationWorkFlowService(ApplicationWorkFlowDAOImpl applicationWorkFlowDAO) {
        this.applicationWorkFlowDAO = applicationWorkFlowDAO;
    }

    @Transactional(readOnly = true)
    public List<ApplicationWorkFlow> getAllApplicationWorkFlows() {
        return applicationWorkFlowDAO.findAll();
    }

    public List<ApplicationWorkFlow> getApplicationsByEmployeeId(int employeeId) {
        return applicationWorkFlowDAO.findByEmployeeID(employeeId).orElse(null);
    }

    // Save a new application if the previous one is approved
    public ApplicationWorkFlow saveApplication(ApplicationWorkFlow applicationWorkFlow) {
        // Get all applications for this employee
        Optional<List<ApplicationWorkFlow>> existingApplications = applicationWorkFlowDAO.findByEmployeeID(applicationWorkFlow.getEmployeeID());

        if (existingApplications.isPresent()) {
            // Unwrap the Optional to get the list
            List<ApplicationWorkFlow> applicationsList = existingApplications.get();
            // Check if there's an unapproved application that blocks new submissions
            for (ApplicationWorkFlow app : applicationsList) {
                if (app.getStatus().equals("Pending")) {
                    throw new IllegalStateException("Cannot submit a new application while " +
                            "another is pending.");
                }
            }
        }

        // Save the new application
        return applicationWorkFlowDAO.save(applicationWorkFlow);
    }

    public String checkOnboardingStatus(int employeeId) {
        Optional<List<ApplicationWorkFlow>> applications = applicationWorkFlowDAO.findByEmployeeID(employeeId);

        if (applications.isEmpty()) {
            return "Never submitted";
        }

        List<ApplicationWorkFlow> applicationsList = applications.get();

        // Find the most recent application by date
        ApplicationWorkFlow latestApplication = applicationsList.stream()
                .max(Comparator.comparing(ApplicationWorkFlow::getLastModificationDate))
                .orElse(null);

        switch (latestApplication.getStatus()) {
            case "Pending":
                return "Please wait for HR to review your applications.";
            case "Rejected":
                String comment = latestApplication.getComment();
                return "Your applications was rejected. Feedback: " + (comment != null && !comment.isEmpty() ? comment : "No feedback provided.");
//                return "Your applications was rejected. Please review the feedback and resubmit.";
            case "Approved":
                return "Application approved. You can proceed to the next step.";
            default:
                return "Unknown applications status.";
        }
    }

    public List<ApplicationWorkFlow> getApplicationsByStatus(String status) {
        return applicationWorkFlowDAO.findByStatus(status);
    }

    public ApplicationWorkFlow updateApplicationStatus(int id, String status, String feedback) {
        Optional<ApplicationWorkFlow> optionalApplication = applicationWorkFlowDAO.findById(id);
        if (optionalApplication.isPresent()) {
            ApplicationWorkFlow application = optionalApplication.get();
            application.setStatus(status);
            application.setComment(feedback);
            application.setLastModificationDate(new Timestamp(System.currentTimeMillis()));
            return applicationWorkFlowDAO.save(application);
        } else {
            return null;
        }
    }
}

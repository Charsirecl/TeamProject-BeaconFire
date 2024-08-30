package com.example.employeeservice.service;

import com.example.employeeservice.domain.Employee;
import com.example.employeeservice.domain.PersonalDocument;
import com.example.employeeservice.domain.VisaStatus;
import com.example.employeeservice.repository.EmployeeDAO;
import com.example.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisaStatusService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeDAO employeeDAO;

    public Employee updateVisaStatus(String employeeId, VisaStatus visaStatus) {
        // Find the employee by employeeId
        Employee employee = employeeDAO.findByCustomId(employeeId).orElse(null);
        if (employee == null) {
            return null; // Employee not found
        }

        // Update the visa status in the employee's visa status list
        List<VisaStatus> visaStatuses = employee.getVisaStatus();
        boolean visaStatusUpdated = false;

        // If the visaStatus has an ID, update the existing one
        if (visaStatus.getId() != null) {
            for (int i = 0; i < visaStatuses.size(); i++) {
                if (visaStatuses.get(i).getId().equals(visaStatus.getId())) {
                    visaStatuses.set(i, visaStatus);
                    visaStatusUpdated = true;
                    break;
                }
            }
        }

        // If not found, add the new visaStatus
        if (!visaStatusUpdated) {
            visaStatuses.add(visaStatus);
        }

        // Save the updated employee back to the database
        employee.setVisaStatus(visaStatuses);
        return employeeRepository.save(employee);
    }

    public Employee updatePersonalDocument(String employeeId, PersonalDocument document) {
        // Find the employee by employeeId
        Employee employee = employeeDAO.findByCustomId(employeeId).orElse(null);
        if (employee == null) {
            return null; // Employee not found
        }

        // Update the personal document list in the employee's record
        List<PersonalDocument> personalDocuments = employee.getPersonalDocument();
        boolean documentUpdated = false;

        // If the document has an ID, update the existing one
        if (document.getId() != null) {
            for (int i = 0; i < personalDocuments.size(); i++) {
                if (personalDocuments.get(i).getId().equals(document.getId())) {
                    personalDocuments.set(i, document);
                    documentUpdated = true;
                    break;
                }
            }
        }

        // If not found, add the new document
        if (!documentUpdated) {
            personalDocuments.add(document);
        }

        // Save the updated employee back to the database
        employee.setPersonalDocument(personalDocuments);
        return employeeRepository.save(employee);
    }
}

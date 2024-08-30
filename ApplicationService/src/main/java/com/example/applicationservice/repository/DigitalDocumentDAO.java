package com.example.applicationservice.repository;

import com.example.applicationservice.domain.DigitalDocument;

import java.util.List;

public interface DigitalDocumentDAO {
    DigitalDocument save(DigitalDocument document);
    List<DigitalDocument> findAll();
    List<DigitalDocument> findByEmployeeId(String employeeId);
}

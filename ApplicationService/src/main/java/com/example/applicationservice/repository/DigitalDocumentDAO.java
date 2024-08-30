package com.example.applicationservice.repository;

import com.example.applicationservice.domain.DigitalDocument;

import java.util.List;
import java.util.Optional;

public interface DigitalDocumentDAO {
    DigitalDocument save(DigitalDocument document);
    List<DigitalDocument> findAll();
    List<DigitalDocument> findByEmployeeId(String employeeId);
}

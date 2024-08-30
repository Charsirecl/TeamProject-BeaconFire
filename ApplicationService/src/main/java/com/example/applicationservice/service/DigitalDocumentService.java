package com.example.applicationservice.service;

import com.example.applicationservice.domain.DigitalDocument;
import com.example.applicationservice.repository.DigitalDocumentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DigitalDocumentService {

    @Autowired
    private DigitalDocumentDAO digitalDocumentDAO;

    public List<DigitalDocument> getAllDocuments() {
        return digitalDocumentDAO.findAll();
    }

    @Transactional
    public DigitalDocument saveDocument(DigitalDocument document) {
        return digitalDocumentDAO.save(document);
    }

    public List<DigitalDocument> getDocumentsByEmployeeId(String employeeId) {
        return digitalDocumentDAO.findByEmployeeId(employeeId);
    }
}


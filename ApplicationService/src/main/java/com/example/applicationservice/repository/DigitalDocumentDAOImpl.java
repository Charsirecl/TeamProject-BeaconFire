package com.example.applicationservice.repository;

import com.example.applicationservice.domain.DigitalDocument;
//import jakarta.persistence.*;
import javax.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DigitalDocumentDAOImpl implements DigitalDocumentDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public DigitalDocument save(DigitalDocument document) {
        if (document.getId() == null) {
            entityManager.persist(document); // Insert new document
            return document;
        } else {
            return entityManager.merge(document); // Update existing document
        }
    }

    @Override
    public List<DigitalDocument> findAll() {
        TypedQuery<DigitalDocument> query = entityManager.createQuery("SELECT d FROM DigitalDocument d", DigitalDocument.class);
        return query.getResultList();
    }

    @Override
    public List<DigitalDocument> findByEmployeeId(String employeeId) {
        TypedQuery<DigitalDocument> query = entityManager.createQuery("SELECT d FROM DigitalDocument d WHERE d.employeeId = :employeeId", DigitalDocument.class);
        query.setParameter("employeeId", employeeId);
        return query.getResultList();
    }
}

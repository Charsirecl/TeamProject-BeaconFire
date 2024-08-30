package com.example.applicationservice.repository;

import com.example.applicationservice.domain.ApplicationWorkFlow;
//import jakarta.persistence.*;
import javax.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class ApplicationWorkFlowDAOImpl implements ApplicationWorkFlowDAO {

//    @Autowired
//    private SessionFactory sessionFactory;
//
//    public List<ApplicationWorkFlow> findAll() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("from ApplicationWorkFlow", ApplicationWorkFlow.class).list();
//    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ApplicationWorkFlow> findAll() {
        List<ApplicationWorkFlow> results = entityManager.createQuery("from ApplicationWorkFlow", ApplicationWorkFlow.class).getResultList();
        System.out.println("Number of results: " + results.size());
        return results;
    }

    @Override
    public Optional<ApplicationWorkFlow> findById(int id) {
        ApplicationWorkFlow applicationWorkFlow = entityManager.find(ApplicationWorkFlow.class, id);
        return Optional.ofNullable(applicationWorkFlow);
    }

    @Override
    public Optional<List<ApplicationWorkFlow>> findByEmployeeID(int employeeId) {
        TypedQuery<ApplicationWorkFlow> query = entityManager.createQuery(
                "SELECT a FROM ApplicationWorkFlow a WHERE a.employeeID = :employeeId",
                ApplicationWorkFlow.class
        );
        query.setParameter("employeeId", employeeId);
        List<ApplicationWorkFlow> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results);
    }

    @Override
    @Transactional
    public ApplicationWorkFlow save(ApplicationWorkFlow applicationWorkFlow) {
        // Find existing applications by EmployeeID
        Optional<List<ApplicationWorkFlow>> existingApplications = findByEmployeeID(applicationWorkFlow.getEmployeeID());

        if (existingApplications.isPresent() && !existingApplications.get().isEmpty()) {
            // There are existing applications, find the one that needs updating (if any)
            for (ApplicationWorkFlow existing : existingApplications.get()) {
                if (existing.getStatus().equals(applicationWorkFlow.getStatus())) {
                    // Update the existing application
                    existing.setStatus(applicationWorkFlow.getStatus());
                    existing.setComment(applicationWorkFlow.getComment());
                    existing.setLastModificationDate(new Timestamp(System.currentTimeMillis()));
                    return entityManager.merge(existing);
                }
            }
        }

        // If no application matches the criteria, create a new one
        applicationWorkFlow.setCreateDate(new Timestamp(System.currentTimeMillis()));
        applicationWorkFlow.setLastModificationDate(new Timestamp(System.currentTimeMillis()));
        entityManager.persist(applicationWorkFlow);
        return applicationWorkFlow;
    }


    @Override
    @Transactional
    public void deleteById(int id) {
        ApplicationWorkFlow applicationWorkFlow = entityManager.find(ApplicationWorkFlow.class, id);
        if (applicationWorkFlow != null) {
            entityManager.remove(applicationWorkFlow);
        }
    }

    @Override
    public List<ApplicationWorkFlow> findByStatus(String status) {
        TypedQuery<ApplicationWorkFlow> query = entityManager.createQuery("SELECT a FROM ApplicationWorkFlow a WHERE a.status = :status", ApplicationWorkFlow.class);
        query.setParameter("status", status);
        return query.getResultList();
    }

}


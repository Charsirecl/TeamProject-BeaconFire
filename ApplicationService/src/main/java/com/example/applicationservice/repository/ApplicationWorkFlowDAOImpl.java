package com.example.applicationservice.repository;

import com.example.applicationservice.domain.ApplicationWorkFlow;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    public Optional<ApplicationWorkFlow> findByEmployeeID(int employeeId) {
        TypedQuery<ApplicationWorkFlow> query = entityManager.createQuery(
                "SELECT a FROM ApplicationWorkFlow a WHERE a.employeeID = :employeeId",
                ApplicationWorkFlow.class
        );
        query.setParameter("employeeId", employeeId);
        List<ApplicationWorkFlow> results = query.getResultList();
        return results.stream().findFirst();
    }

    @Override
    @Transactional
    public ApplicationWorkFlow save(ApplicationWorkFlow applicationWorkFlow) {
        Optional<ApplicationWorkFlow> existingApplication = findByEmployeeID(applicationWorkFlow.getEmployeeID());

        if (existingApplication.isPresent()) {
            // Update existing application
            ApplicationWorkFlow existing = existingApplication.get();
            existing.setStatus(applicationWorkFlow.getStatus());
            existing.setComment(applicationWorkFlow.getComment());
            existing.setLastModificationDate(new Timestamp(System.currentTimeMillis()));
            return entityManager.merge(existing);
        } else {
            // Create a new application
            applicationWorkFlow.setCreateDate(new Timestamp(System.currentTimeMillis()));
            applicationWorkFlow.setLastModificationDate(new Timestamp(System.currentTimeMillis()));
            entityManager.persist(applicationWorkFlow);
            return applicationWorkFlow;
        }
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        ApplicationWorkFlow applicationWorkFlow = entityManager.find(ApplicationWorkFlow.class, id);
        if (applicationWorkFlow != null) {
            entityManager.remove(applicationWorkFlow);
        }
    }

}


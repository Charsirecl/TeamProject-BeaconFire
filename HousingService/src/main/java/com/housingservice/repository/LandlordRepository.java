package com.housingservice.repository;

import com.housingservice.model.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandlordRepository extends JpaRepository<Landlord, Integer> {
    // Custom query to find landlords by email
    Landlord findByEmail(String email);
}

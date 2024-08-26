package com.housingservice.repository;

import com.housingservice.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Integer> {
    // Custom query to find houses by landlord ID
    List<House> findByLandlordId(Integer landlordId);
}

package com.housingservice.repository;

import com.housingservice.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer> {
    // Custom query to find facilities by house ID
    List<Facility> findByHouseId(Integer houseId);
}

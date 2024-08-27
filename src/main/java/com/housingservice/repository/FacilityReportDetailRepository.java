package com.housingservice.repository;

import com.housingservice.model.FacilityReportDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityReportDetailRepository extends JpaRepository<FacilityReportDetail, Integer> {

    // Custom query to find details by facility report ID
    List<FacilityReportDetail> findByFacilityReportId(Integer facilityReportId);

    // Custom query to find details by employee ID
    List<FacilityReportDetail> findByEmployeeID(Integer employeeID);  // Corrected: matches the entity property name
}

package com.housingservice.repository;

import com.housingservice.model.FacilityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface FacilityReportRepository extends JpaRepository<FacilityReport, Integer> {

    // Custom query to find reports by facility ID
    List<FacilityReport> findByFacilityId(Integer facilityId);

    // Custom query to find reports by employee ID
    List<FacilityReport> findByEmployeeId(Integer employeeId);

    // Custom query to find reports by status
    List<FacilityReport> findByStatus(String status);

    // Custom query to find reports by title
    @Query("SELECT fr FROM FacilityReport fr WHERE fr.title LIKE %:title%")
    List<FacilityReport> findReportsByTitleContaining(@Param("title") String title);
}

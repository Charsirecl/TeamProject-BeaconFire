package com.housingservice.repository;

import com.housingservice.model.FacilityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface FacilityReportRepository extends JpaRepository<FacilityReport, Integer> {

    List<FacilityReport> findByFacilityId(Integer facilityId);

    List<FacilityReport> findByEmployeeID(Integer employeeID);

    List<FacilityReport> findByStatus(String status);

    @Query("SELECT fr FROM FacilityReport fr WHERE fr.title LIKE %:title%")
    List<FacilityReport> findReportsByTitleContaining(@Param("title") String title);
}

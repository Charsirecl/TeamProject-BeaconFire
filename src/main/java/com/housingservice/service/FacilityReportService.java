package com.housingservice.service;

import com.housingservice.model.FacilityReport;
import com.housingservice.model.Facility;
import com.housingservice.repository.FacilityReportRepository;
import com.housingservice.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityReportService {

    @Autowired
    private FacilityReportRepository facilityReportRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    public List<FacilityReport> getAllFacilityReports() {
        return facilityReportRepository.findAll();
    }

    public Optional<FacilityReport> getFacilityReportById(Integer id) {
        return facilityReportRepository.findById(id);
    }

    public FacilityReport addFacilityReport(FacilityReport facilityReport) {
        // Ensure the Facility entity is set correctly
        Optional<Facility> facility = facilityRepository.findById(facilityReport.getFacility().getId());
        if (facility.isPresent()) {
            facilityReport.setFacility(facility.get());
        } else {
            throw new IllegalArgumentException("Invalid facilityId");
        }
        return facilityReportRepository.save(facilityReport);
    }

    public FacilityReport updateFacilityReport(FacilityReport facilityReport) {
        // Ensure the Facility entity is set correctly
        Optional<Facility> facility = facilityRepository.findById(facilityReport.getFacility().getId());
        if (facility.isPresent()) {
            facilityReport.setFacility(facility.get());
        } else {
            throw new IllegalArgumentException("Invalid facilityId");
        }
        return facilityReportRepository.save(facilityReport);
    }

    public void deleteFacilityReport(Integer id) {
        facilityReportRepository.deleteById(id);
    }
}

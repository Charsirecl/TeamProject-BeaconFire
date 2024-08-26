package com.housingservice.service;

import com.housingservice.dto.FacilityReportDTO;
import com.housingservice.model.FacilityReport;
import com.housingservice.repository.FacilityReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityReportService {

    @Autowired
    private FacilityReportRepository facilityReportRepository;

    public List<FacilityReport> getAllReports() {
        return facilityReportRepository.findAll();
    }

    public Optional<FacilityReport> getReportById(int id) {
        return facilityReportRepository.findById(id);
    }

    public FacilityReport createReport(FacilityReportDTO facilityReportDTO) {
        // Logic to create and save a new FacilityReport
        FacilityReport facilityReport = new FacilityReport();
        // Map fields from facilityReportDTO to facilityReport
        return facilityReportRepository.save(facilityReport);
    }

    public FacilityReport updateReport(int id, FacilityReportDTO facilityReportDTO) {
        // Logic to update an existing FacilityReport
        Optional<FacilityReport> reportOptional = facilityReportRepository.findById(id);
        if (reportOptional.isPresent()) {
            FacilityReport facilityReport = reportOptional.get();
            // Update fields with data from facilityReportDTO
            return facilityReportRepository.save(facilityReport);
        }
        return null;
    }

    public void deleteReport(int id) {
        facilityReportRepository.deleteById(id);
    }
}
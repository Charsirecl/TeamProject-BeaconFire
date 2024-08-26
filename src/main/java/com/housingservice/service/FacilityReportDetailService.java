package com.housingservice.service;

import com.housingservice.dto.FacilityReportDetailDTO;
import com.housingservice.model.FacilityReportDetail;
import com.housingservice.repository.FacilityReportDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityReportDetailService {

    @Autowired
    private FacilityReportDetailRepository facilityReportDetailRepository;

    public List<FacilityReportDetail> getAllReportDetails() {
        return facilityReportDetailRepository.findAll();
    }

    public Optional<FacilityReportDetail> getReportDetailById(int id) {
        return facilityReportDetailRepository.findById(id);
    }

    public FacilityReportDetail createReportDetail(FacilityReportDetailDTO facilityReportDetailDTO) {
        // Logic to create and save a new FacilityReportDetail
        FacilityReportDetail reportDetail = new FacilityReportDetail();
        // Map fields from facilityReportDetailDTO to reportDetail
        return facilityReportDetailRepository.save(reportDetail);
    }

    public FacilityReportDetail updateReportDetail(int id, FacilityReportDetailDTO facilityReportDetailDTO) {
        // Logic to update an existing FacilityReportDetail
        Optional<FacilityReportDetail> reportDetailOptional = facilityReportDetailRepository.findById(id);
        if (reportDetailOptional.isPresent()) {
            FacilityReportDetail reportDetail = reportDetailOptional.get();
            // Update fields with data from facilityReportDetailDTO
            return facilityReportDetailRepository.save(reportDetail);
        }
        return null;
    }

    public void deleteReportDetail(int id) {
        facilityReportDetailRepository.deleteById(id);
    }
}
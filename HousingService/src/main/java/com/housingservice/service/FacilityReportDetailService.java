package com.housingservice.service;

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


    public List<FacilityReportDetail> getAllFacilityReportDetails() {
        return facilityReportDetailRepository.findAll();
    }

    public Optional<FacilityReportDetail> getFacilityReportDetailById(Integer id) {
        return facilityReportDetailRepository.findById(id);
    }

    public FacilityReportDetail addFacilityReportDetail(FacilityReportDetail facilityReportDetail) {
        return facilityReportDetailRepository.save(facilityReportDetail);
    }


    public FacilityReportDetail updateFacilityReportDetail(FacilityReportDetail facilityReportDetail) {
        return facilityReportDetailRepository.save(facilityReportDetail);
    }


    public void deleteFacilityReportDetail(Integer id) {
        facilityReportDetailRepository.deleteById(id);
    }


    public List<FacilityReportDetail> getFacilityReportDetailsByFacilityReportId(Integer facilityReportId) {
        return facilityReportDetailRepository.findByFacilityReportId(facilityReportId);
    }
}

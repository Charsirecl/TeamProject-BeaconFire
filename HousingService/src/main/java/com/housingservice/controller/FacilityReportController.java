package com.housingservice.controller;

import com.housingservice.dto.FacilityReportDTO;
import com.housingservice.dto.FacilityReportDetailDTO;
import com.housingservice.model.FacilityReport;
import com.housingservice.model.FacilityReportDetail;
import com.housingservice.service.FacilityReportService;
import com.housingservice.util.HousingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.housingservice.service.FacilityReportDetailService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/facility-reports")
public class FacilityReportController {

    @Autowired
    private FacilityReportService facilityReportService;
    @Autowired
    private FacilityReportDetailService facilityReportDetailService;

    @Autowired
    private HousingMapper housingMapper;

    @PostMapping
    public ResponseEntity<FacilityReportDTO> createFacilityReport(@RequestBody FacilityReportDTO facilityReportDTO) {
        FacilityReport facilityReport = housingMapper.toFacilityReportEntity(facilityReportDTO);
        FacilityReport createdReport = facilityReportService.addFacilityReport(facilityReport);
        return ResponseEntity.ok(housingMapper.toFacilityReportDTO(createdReport));
    }


    @GetMapping
    public ResponseEntity<List<FacilityReportDTO>> getAllFacilityReports() {
        List<FacilityReportDTO> reports = facilityReportService.getAllFacilityReports()
                .stream()
                .map(housingMapper::toFacilityReportDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reports);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityReportDTO> getFacilityReportById(@PathVariable Integer id) {
        return facilityReportService.getFacilityReportById(id)
                .map(housingMapper::toFacilityReportDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<List<FacilityReportDetailDTO>> getFacilityReportDetails(@PathVariable Integer id) {
        List<FacilityReportDetailDTO> details = facilityReportDetailService.getFacilityReportDetailsByFacilityReportId(id)
                .stream()
                .map(housingMapper::toFacilityReportDetailDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(details);
    }

    @PostMapping("/{id}/details")
    public ResponseEntity<FacilityReportDetailDTO> addFacilityReportDetail(
            @PathVariable Integer id,
            @RequestBody FacilityReportDetailDTO detailDTO) {


        FacilityReport facilityReport = facilityReportService.getFacilityReportById(id)
                .orElseThrow(() -> new RuntimeException("FacilityReport not found"));
        detailDTO.setFacilityReportId(id);
        FacilityReportDetail detail = housingMapper.toFacilityReportDetailEntity(detailDTO);
        detail.setFacilityReport(facilityReport);
        FacilityReportDetail createdDetail = facilityReportDetailService.addFacilityReportDetail(detail);
        return ResponseEntity.ok(housingMapper.toFacilityReportDetailDTO(createdDetail));
    }



    @PutMapping("/details/{detailId}")
    public ResponseEntity<FacilityReportDetailDTO> updateFacilityReportDetail(
            @PathVariable Integer detailId,
            @RequestBody FacilityReportDetailDTO detailDTO) {


        FacilityReportDetail existingDetail = facilityReportDetailService.getFacilityReportDetailById(detailId)
                .orElseThrow(() -> new RuntimeException("FacilityReportDetail not found"));
        existingDetail.setComment(detailDTO.getComment());
        existingDetail.setEmployeeID(detailDTO.getEmployeeID());
        
        FacilityReportDetail updatedDetail = facilityReportDetailService.updateFacilityReportDetail(existingDetail);

        return ResponseEntity.ok(housingMapper.toFacilityReportDetailDTO(updatedDetail));
    }


    @DeleteMapping("/details/{detailId}")
    public ResponseEntity<Void> deleteFacilityReportDetail(@PathVariable Integer detailId) {
        facilityReportDetailService.deleteFacilityReportDetail(detailId);
        return ResponseEntity.noContent().build();
    }
}

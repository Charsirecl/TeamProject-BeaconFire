package com.housingservice.controller;

import com.housingservice.dto.FacilityReportDetailDTO;
import com.housingservice.model.FacilityReportDetail;
import com.housingservice.service.FacilityReportDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/report-details")
public class FacilityReportDetailController {

    @Autowired
    private FacilityReportDetailService facilityReportDetailService;

    @GetMapping
    public List<FacilityReportDetail> getAllReportDetails() {
        return facilityReportDetailService.getAllReportDetails();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityReportDetail> getReportDetailById(@PathVariable int id) {
        Optional<FacilityReportDetail> facilityReportDetail = facilityReportDetailService.getReportDetailById(id);
        return facilityReportDetail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public FacilityReportDetail createReportDetail(@RequestBody FacilityReportDetailDTO facilityReportDetailDTO) {
        return facilityReportDetailService.createReportDetail(facilityReportDetailDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacilityReportDetail> updateReportDetail(@PathVariable int id, @RequestBody FacilityReportDetailDTO facilityReportDetailDTO) {
        FacilityReportDetail updatedReportDetail = facilityReportDetailService.updateReportDetail(id, facilityReportDetailDTO);
        return updatedReportDetail != null ? ResponseEntity.ok(updatedReportDetail) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReportDetail(@PathVariable int id) {
        facilityReportDetailService.deleteReportDetail(id);
        return ResponseEntity.noContent().build();
    }
}

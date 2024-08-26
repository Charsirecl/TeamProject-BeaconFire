package com.housingservice.controller;

import com.housingservice.dto.FacilityReportDTO;
import com.housingservice.model.FacilityReport;
import com.housingservice.service.FacilityReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
public class FacilityReportController {

    @Autowired
    private FacilityReportService facilityReportService;

    @GetMapping
    public List<FacilityReport> getAllReports() {
        return facilityReportService.getAllReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacilityReport> getReportById(@PathVariable int id) {
        Optional<FacilityReport> facilityReport = facilityReportService.getReportById(id);
        return facilityReport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public FacilityReport createReport(@RequestBody FacilityReportDTO facilityReportDTO) {
        return facilityReportService.createReport(facilityReportDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacilityReport> updateReport(@PathVariable int id, @RequestBody FacilityReportDTO facilityReportDTO) {
        FacilityReport updatedReport = facilityReportService.updateReport(id, facilityReportDTO);
        return updatedReport != null ? ResponseEntity.ok(updatedReport) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable int id) {
        facilityReportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}
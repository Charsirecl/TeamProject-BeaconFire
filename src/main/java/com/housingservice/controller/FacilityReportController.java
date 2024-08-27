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

    // Create a Facility Report
//    @PostMapping
//    public ResponseEntity<FacilityReportDTO> createFacilityReport(@RequestBody FacilityReportDTO facilityReportDTO) {
//        FacilityReport facilityReport = housingMapper.toFacilityReportEntity(facilityReportDTO);
//        FacilityReport createdReport = facilityReportService.addFacilityReport(facilityReport);
//        return ResponseEntity.ok(housingMapper.toFacilityReportDTO(createdReport));
//    }

    @PostMapping
    public ResponseEntity<FacilityReportDTO> createFacilityReport(@RequestBody FacilityReportDTO facilityReportDTO) {
        // Map DTO to Entity
        FacilityReport facilityReport = housingMapper.toFacilityReportEntity(facilityReportDTO);

        // Save the Facility Report along with its details
        FacilityReport createdReport = facilityReportService.addFacilityReport(facilityReport);
        return ResponseEntity.ok(housingMapper.toFacilityReportDTO(createdReport));
    }

    // View existing Facility Reports
    @GetMapping
    public ResponseEntity<List<FacilityReportDTO>> getAllFacilityReports() {
        List<FacilityReportDTO> reports = facilityReportService.getAllFacilityReports()
                .stream()
                .map(housingMapper::toFacilityReportDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reports);
    }

    // View a specific Facility Report by ID
    @GetMapping("/{id}")
    public ResponseEntity<FacilityReportDTO> getFacilityReportById(@PathVariable Integer id) {
        return facilityReportService.getFacilityReportById(id)
                .map(housingMapper::toFacilityReportDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get comments (FacilityReportDetails) for a specific Facility Report
    @GetMapping("/{id}/details")
    public ResponseEntity<List<FacilityReportDetailDTO>> getFacilityReportDetails(@PathVariable Integer id) {
        List<FacilityReportDetailDTO> details = facilityReportDetailService.getFacilityReportDetailsByFacilityReportId(id)
                .stream()
                .map(housingMapper::toFacilityReportDetailDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(details);
    }

//    // Add a comment (FacilityReportDetail) to a Facility Report
//    @PostMapping("/{id}/details")
//    public ResponseEntity<FacilityReportDetailDTO> addFacilityReportDetail(
//            @PathVariable Integer id,
//            @RequestBody FacilityReportDetailDTO detailDTO) {
//
//        // Fetch the FacilityReport entity using the provided id
//        FacilityReport facilityReport = facilityReportService.getFacilityReportById(id)
//                .orElseThrow(() -> new RuntimeException("FacilityReport not found"));
//
//        FacilityReportDetail detail = housingMapper.toFacilityReportDetailEntity(detailDTO);
//        detail.setFacilityReport(facilityReport); // Set the FacilityReport on the detail entity
//        FacilityReportDetail createdDetail = facilityReportDetailService.addFacilityReportDetail(detail);
//
//        return ResponseEntity.ok(housingMapper.toFacilityReportDetailDTO(createdDetail));
//    }

    @PostMapping("/{id}/details")
    public ResponseEntity<FacilityReportDetailDTO> addFacilityReportDetail(
            @PathVariable Integer id,
            @RequestBody FacilityReportDetailDTO detailDTO) {

        // Fetch the FacilityReport entity using the provided id
        FacilityReport facilityReport = facilityReportService.getFacilityReportById(id)
                .orElseThrow(() -> new RuntimeException("FacilityReport not found"));

        // Set the FacilityReportId in the DTO
        detailDTO.setFacilityReportId(id);

        // Map the DTO to Entity
        FacilityReportDetail detail = housingMapper.toFacilityReportDetailEntity(detailDTO);

        // Set the FacilityReport on the detail entity
        detail.setFacilityReport(facilityReport);

        // Save the FacilityReportDetail
        FacilityReportDetail createdDetail = facilityReportDetailService.addFacilityReportDetail(detail);

        // Return the saved FacilityReportDetail as a DTO
        return ResponseEntity.ok(housingMapper.toFacilityReportDetailDTO(createdDetail));
    }



    // Update a comment (FacilityReportDetail)
//    @PutMapping("/details/{detailId}")
//    public ResponseEntity<FacilityReportDetailDTO> updateFacilityReportDetail(
//            @PathVariable Integer detailId,
//            @RequestBody FacilityReportDetailDTO detailDTO) {
//        FacilityReportDetail detail = housingMapper.toFacilityReportDetailEntity(detailDTO);
//        detail.setId(detailId);
//        FacilityReportDetail updatedDetail = facilityReportDetailService.updateFacilityReportDetail(detail);
//        return ResponseEntity.ok(housingMapper.toFacilityReportDetailDTO(updatedDetail));
//    }

    // Update a comment (FacilityReportDetail) wihtout facilityReportID
    @PutMapping("/details/{detailId}")
    public ResponseEntity<FacilityReportDetailDTO> updateFacilityReportDetail(
            @PathVariable Integer detailId,
            @RequestBody FacilityReportDetailDTO detailDTO) {

        // Retrieve the existing FacilityReportDetail to ensure the FacilityReport relationship is maintained
        FacilityReportDetail existingDetail = facilityReportDetailService.getFacilityReportDetailById(detailId)
                .orElseThrow(() -> new RuntimeException("FacilityReportDetail not found"));

        // Update the existing detail with the new values from DTO
        existingDetail.setComment(detailDTO.getComment());
        existingDetail.setEmployeeID(detailDTO.getEmployeeID());

        // Save the updated detail
        FacilityReportDetail updatedDetail = facilityReportDetailService.updateFacilityReportDetail(existingDetail);

        return ResponseEntity.ok(housingMapper.toFacilityReportDetailDTO(updatedDetail));
    }


    // Users can delete only their own comments
    @DeleteMapping("/details/{detailId}")
    public ResponseEntity<Void> deleteFacilityReportDetail(@PathVariable Integer detailId) {
        facilityReportDetailService.deleteFacilityReportDetail(detailId);
        return ResponseEntity.noContent().build();
    }
}

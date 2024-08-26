package com.housingservice.dto;

import java.util.List;

public class FacilityReportDTO {
    private Integer id;
    private Integer facilityId;
    private Integer employeeId;
    private String title;
    private String description;
    private String status;
    private List<FacilityReportDetailDTO> facilityReportDetails;

    // Constructors
    public FacilityReportDTO() {}

    public FacilityReportDTO(Integer id, Integer facilityId, Integer employeeId, String title, String description, String status, List<FacilityReportDetailDTO> facilityReportDetails) {
        this.id = id;
        this.facilityId = facilityId;
        this.employeeId = employeeId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.facilityReportDetails = facilityReportDetails;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FacilityReportDetailDTO> getFacilityReportDetails() {
        return facilityReportDetails;
    }

    public void setFacilityReportDetails(List<FacilityReportDetailDTO> facilityReportDetails) {
        this.facilityReportDetails = facilityReportDetails;
    }
}

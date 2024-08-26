package com.housingservice.dto;

public class FacilityReportDetailDTO {
    private Integer id;
    private Integer facilityReportId;
    private Integer employeeId;
    private String comment;

    // Constructors
    public FacilityReportDetailDTO() {}

    public FacilityReportDetailDTO(Integer id, Integer facilityReportId, Integer employeeId, String comment) {
        this.id = id;
        this.facilityReportId = facilityReportId;
        this.employeeId = employeeId;
        this.comment = comment;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFacilityReportId() {
        return facilityReportId;
    }

    public void setFacilityReportId(Integer facilityReportId) {
        this.facilityReportId = facilityReportId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

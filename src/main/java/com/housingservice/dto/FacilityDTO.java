package com.housingservice.dto;

import java.util.List;

public class FacilityDTO {
    private Integer id;
    private Integer houseId;
    private String type;
    private Integer quantity;
    private String description;
    private List<FacilityReportDTO> facilityReports;

    // Constructors
    public FacilityDTO() {}

    public FacilityDTO(Integer id, Integer houseId, String type, Integer quantity, String description, List<FacilityReportDTO> facilityReports) {
        this.id = id;
        this.houseId = houseId;
        this.type = type;
        this.quantity = quantity;
        this.description = description;
        this.facilityReports = facilityReports;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FacilityReportDTO> getFacilityReports() {
        return facilityReports;
    }

    public void setFacilityReports(List<FacilityReportDTO> facilityReports) {
        this.facilityReports = facilityReports;
    }
}

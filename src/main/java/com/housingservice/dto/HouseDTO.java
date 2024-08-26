package com.housingservice.dto;

import java.util.List;

public class HouseDTO {
    private Integer id;
    private Integer landlordId;
    private String address;
    private Integer maxOccupant;
    private String description;
    private List<FacilityDTO> facilities;
    private List<Integer> roommateIds;

    public HouseDTO(){}

    public HouseDTO(Integer id, Integer landlordId, String address, Integer maxOccupant, String description, List<FacilityDTO> facilities, List<Integer> roommateIds) {
        this.id = id;
        this.landlordId = landlordId;
        this.address = address;
        this.maxOccupant = maxOccupant;
        this.description = description;
        this.facilities = facilities;
        this.roommateIds = roommateIds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(Integer landlordId) {
        this.landlordId = landlordId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMaxOccupant() {
        return maxOccupant;
    }

    public void setMaxOccupant(Integer maxOccupant) {
        this.maxOccupant = maxOccupant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FacilityDTO> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<FacilityDTO> facilities) {
        this.facilities = facilities;
    }

    public List<Integer> getRoommateIds() {
        return roommateIds;
    }

    public void setRoommateIds(List<Integer> roommateIds) {
        this.roommateIds = roommateIds;
    }
}
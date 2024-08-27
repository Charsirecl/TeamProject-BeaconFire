package com.housingservice.util;

import com.housingservice.dto.*;
import com.housingservice.model.*;

import java.util.stream.Collectors;

public class Mapper {

    public static HouseDTO toHouseDTO(House house) {
        if (house == null) return null;

        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setId(house.getId());
        houseDTO.setLandlordId(house.getLandlord().getId());
        houseDTO.setAddress(house.getAddress());
        houseDTO.setMaxOccupant(house.getMaxOccupant());
        houseDTO.setDescription(house.getDescription());

        // Map facilities
        houseDTO.setFacilities(house.getFacilities().stream()
                .map(Mapper::toFacilityDTO)
                .collect(Collectors.toList()));

        // Map roommate IDs
        houseDTO.setRoommateIds(house.getRoommateIds());

        return houseDTO;
    }

    public static FacilityDTO toFacilityDTO(Facility facility) {
        if (facility == null) return null;

        FacilityDTO facilityDTO = new FacilityDTO();
        facilityDTO.setId(facility.getId());
        facilityDTO.setHouseId(facility.getHouse().getId());
        facilityDTO.setType(facility.getType());
        facilityDTO.setQuantity(facility.getQuantity());
        facilityDTO.setDescription(facility.getDescription());

        // Map facility reports
        facilityDTO.setFacilityReports(facility.getFacilityReports().stream()
                .map(Mapper::toFacilityReportDTO)
                .collect(Collectors.toList()));

        return facilityDTO;
    }

    public static FacilityReportDTO toFacilityReportDTO(FacilityReport facilityReport) {
        if (facilityReport == null) return null;

        FacilityReportDTO facilityReportDTO = new FacilityReportDTO();
        facilityReportDTO.setId(facilityReport.getId());
        facilityReportDTO.setFacilityId(facilityReport.getFacility().getId());
        facilityReportDTO.setEmployeeId(facilityReport.getEmployeeID());
        facilityReportDTO.setTitle(facilityReport.getTitle());
        facilityReportDTO.setDescription(facilityReport.getDescription());
        facilityReportDTO.setStatus(facilityReport.getStatus());

        // Map facility report details
        facilityReportDTO.setFacilityReportDetails(facilityReport.getFacilityReportDetails().stream()
                .map(Mapper::toFacilityReportDetailDTO)
                .collect(Collectors.toList()));

        return facilityReportDTO;
    }

    public static FacilityReportDetailDTO toFacilityReportDetailDTO(FacilityReportDetail facilityReportDetail) {
        if (facilityReportDetail == null) return null;

        FacilityReportDetailDTO facilityReportDetailDTO = new FacilityReportDetailDTO();
        facilityReportDetailDTO.setId(facilityReportDetail.getId());
        facilityReportDetailDTO.setFacilityReportId(facilityReportDetail.getFacilityReport().getId());
        facilityReportDetailDTO.setEmployeeId(facilityReportDetail.getEmployeeID());
        facilityReportDetailDTO.setComment(facilityReportDetail.getComment());

        return facilityReportDetailDTO;
    }

    // Reverse Mappings

    public static House toHouse(HouseDTO houseDTO) {
        if (houseDTO == null) return null;

        House house = new House();
        house.setId(houseDTO.getId());
        // Assuming you have a method to fetch a Landlord by ID
        // house.setLandlord(landlordService.getLandlordById(houseDTO.getLandlordId()));
        house.setAddress(houseDTO.getAddress());
        house.setMaxOccupant(houseDTO.getMaxOccupant());
        house.setDescription(houseDTO.getDescription());

        // Convert DTO facilities to entity facilities
        house.setFacilities(houseDTO.getFacilities().stream()
                .map(Mapper::toFacility)
                .collect(Collectors.toList()));

        // Convert DTO roommates to entity roommates
        // Assuming House has a method to set roommates
        // house.setRoommates(houseDTO.getRoommates().stream()
        //         .map(Mapper::toEmployee)
        //         .collect(Collectors.toList()));

        return house;
    }

    public static Facility toFacility(FacilityDTO facilityDTO) {
        if (facilityDTO == null) return null;

        Facility facility = new Facility();
        facility.setId(facilityDTO.getId());
        // Assuming you have a method to fetch a House by ID
        // facility.setHouse(houseService.getHouseById(facilityDTO.getHouseId()));
        facility.setType(facilityDTO.getType());
        facility.setQuantity(facilityDTO.getQuantity());
        facility.setDescription(facilityDTO.getDescription());

        // Convert DTO facility reports to entity facility reports
        facility.setFacilityReports(facilityDTO.getFacilityReports().stream()
                .map(Mapper::toFacilityReport)
                .collect(Collectors.toList()));

        return facility;
    }

    public static FacilityReport toFacilityReport(FacilityReportDTO facilityReportDTO) {
        if (facilityReportDTO == null) return null;

        FacilityReport facilityReport = new FacilityReport();
        facilityReport.setId(facilityReportDTO.getId());
        // Assuming you have a method to fetch a Facility by ID
        // facilityReport.setFacility(facilityService.getFacilityById(facilityReportDTO.getFacilityId()));
        facilityReport.setEmployeeID(facilityReportDTO.getEmployeeId());
        facilityReport.setTitle(facilityReportDTO.getTitle());
        facilityReport.setDescription(facilityReportDTO.getDescription());
        facilityReport.setStatus(facilityReportDTO.getStatus());

        // Convert DTO facility report details to entity facility report details
        facilityReport.setFacilityReportDetails(facilityReportDTO.getFacilityReportDetails().stream()
                .map(Mapper::toFacilityReportDetail)
                .collect(Collectors.toList()));

        return facilityReport;
    }

    public static FacilityReportDetail toFacilityReportDetail(FacilityReportDetailDTO facilityReportDetailDTO) {
        if (facilityReportDetailDTO == null) return null;

        FacilityReportDetail facilityReportDetail = new FacilityReportDetail();
        facilityReportDetail.setId(facilityReportDetailDTO.getId());
        // Assuming you have a method to fetch a FacilityReport by ID
        // facilityReportDetail.setFacilityReport(facilityReportService.getReportById(facilityReportDetailDTO.getFacilityReportId()));
        facilityReportDetail.setEmployeeID(facilityReportDetailDTO.getEmployeeId());
        facilityReportDetail.setComment(facilityReportDetailDTO.getComment());

        return facilityReportDetail;
    }
}

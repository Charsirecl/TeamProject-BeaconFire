package com.housingservice.util;

import com.housingservice.dto.*;
import com.housingservice.model.*;
import com.housingservice.service.FacilityReportService;
import com.housingservice.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import com.housingservice.client.EmployeeClient;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HousingMapper {

    @Autowired
    private FacilityService facilityService;
//    @Autowired
//    private EmployeeClient employeeClient;

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    private FacilityReportService facilityReportService;
//    // Mapping between House and HouseDTO
//    public HouseDTO toHouseDTO(House house) {
//        HouseDTO dto = new HouseDTO();
//        dto.setId(house.getId());
//        dto.setLandlordId(house.getLandlord().getId());
//        dto.setAddress(house.getAddress());
//        dto.setMaxOccupant(house.getMaxOccupant());
//        dto.setDescription(house.getDescription());
//        dto.setCreateDate(house.getCreateDate());
//        dto.setLastModificationDate(house.getLastModificationDate());
//
//        // Map facilities
//        List<FacilityDTO> facilities = house.getFacilities().stream()
//                .map(this::toFacilityDTO)
//                .collect(Collectors.toList());
//        dto.setFacilities(facilities);
//        // Assuming you have a method to get the list of employee IDs assigned to the house
//
//
////        List<Integer> employeeIds = getEmployeeIdsForHouse(house.getId());
////        dto.setEmployeeIds(employeeIds);
//
//
//        return dto;
//    }

    public HouseDTO toHouseDTO(House house) {
        HouseDTO dto = new HouseDTO();
        dto.setId(house.getId());
        dto.setLandlordId(house.getLandlord().getId());
        dto.setAddress(house.getAddress());
        dto.setMaxOccupant(house.getMaxOccupant());
        dto.setDescription(house.getDescription());
        dto.setCreateDate(house.getCreateDate());
        dto.setLastModificationDate(house.getLastModificationDate());

        // Map facilities
        List<FacilityDTO> facilities = house.getFacilities().stream()
                .map(this::toFacilityDTO)
                .collect(Collectors.toList());
        dto.setFacilities(facilities);

        // Fetch employee IDs dynamically based on houseId
        List<Integer> employeeIds = employeeClient.getEmployeeIdsByHouseId(house.getId());
        dto.setEmployeeIds(employeeIds);

        return dto;
    }



//    public House toHouseEntity(HouseDTO dto) {
//        House house = new House();
//        house.setId(dto.getId());
//
//        // Manually setting the landlord object using landlordId from DTO
//        Landlord landlord = new Landlord();
//        landlord.setId(dto.getLandlordId());
//        house.setLandlord(landlord);
//
//        house.setAddress(dto.getAddress());
//        house.setMaxOccupant(dto.getMaxOccupant());
//        house.setDescription(dto.getDescription());
//
//        // Map facilities
//        List<Facility> facilities = dto.getFacilities().stream()
//                .map(this::toFacilityEntity)
//                .collect(Collectors.toList());
//        house.setFacilities(facilities);
//
//        return house;
//    }

    public House toHouseEntity(HouseDTO dto) {
        House house = new House();
        house.setId(dto.getId());

        // Manually setting the landlord object using landlordId from DTO
        if (dto.getLandlordId() != null) {
            Landlord landlord = new Landlord();
            landlord.setId(dto.getLandlordId());
            house.setLandlord(landlord);
        } else {
            throw new IllegalArgumentException("Landlord ID cannot be null");
        }

        house.setAddress(dto.getAddress());
        house.setMaxOccupant(dto.getMaxOccupant());
        house.setDescription(dto.getDescription());

        // Map facilities, only if they are not null
        if (dto.getFacilities() != null) {
            List<Facility> facilities = dto.getFacilities().stream()
                    .map(this::toFacilityEntity)
                    .collect(Collectors.toList());
            house.setFacilities(facilities);
        } else {
            house.setFacilities(Collections.emptyList());
        }

        return house;
    }

    // Mapping between Facility and FacilityDTO
    public FacilityDTO toFacilityDTO(Facility facility) {
        FacilityDTO dto = new FacilityDTO();
        dto.setId(facility.getId());
        dto.setHouseId(facility.getHouse().getId());
        dto.setType(facility.getType());
        dto.setQuantity(facility.getQuantity());
        dto.setDescription(facility.getDescription());
        dto.setCreateDate(facility.getCreateDate());
        dto.setLastModificationDate(facility.getLastModificationDate());
        return dto;
    }

    public Facility toFacilityEntity(FacilityDTO dto) {
        Facility facility = new Facility();
        facility.setId(dto.getId());

        // Manually setting the house object using houseId from DTO
        House house = new House();
        house.setId(dto.getHouseId());
        facility.setHouse(house);

        facility.setType(dto.getType());
        facility.setQuantity(dto.getQuantity());
        facility.setDescription(dto.getDescription());
        return facility;
    }

    public FacilityReportDTO toFacilityReportDTO(FacilityReport facilityReport) {
        FacilityReportDTO dto = new FacilityReportDTO();
        dto.setId(facilityReport.getId());

        // Check if the facility is not null before accessing its ID
        if (facilityReport.getFacility() != null) {
            dto.setFacilityId(facilityReport.getFacility().getId());
        } else {
            throw new RuntimeException("Facility is null in FacilityReport with ID: " + facilityReport.getId());
        }

        dto.setEmployeeID(facilityReport.getEmployeeID());
        dto.setTitle(facilityReport.getTitle());
        dto.setDescription(facilityReport.getDescription());
        dto.setStatus(facilityReport.getStatus());
        dto.setCreateDate(facilityReport.getCreateDate());
        dto.setLastModificationDate(facilityReport.getLastModificationDate());

        // Map facility report details if available
        if (facilityReport.getFacilityReportDetails() != null) {
            List<FacilityReportDetailDTO> details = facilityReport.getFacilityReportDetails().stream()
                    .map(this::toFacilityReportDetailDTO)
                    .collect(Collectors.toList());
            dto.setFacilityReportDetails(details);
        }

        return dto;
    }


//    public FacilityReport toFacilityReportEntity(FacilityReportDTO dto) {
//        FacilityReport facilityReport = new FacilityReport();
//        facilityReport.setId(dto.getId());
//
//        // Ensure the Facility is correctly set
//        Facility facility = facilityService.getFacilityById(dto.getFacilityId())
//                .orElseThrow(() -> new RuntimeException("Facility not found"));
//        facilityReport.setFacility(facility);
//
//        facilityReport.setEmployeeID(dto.getEmployeeID());
//        facilityReport.setTitle(dto.getTitle());
//        facilityReport.setDescription(dto.getDescription());
//        facilityReport.setStatus(dto.getStatus());
//
//        // Map facility report details if available
//        if (dto.getFacilityReportDetails() != null) {
//            List<FacilityReportDetail> details = dto.getFacilityReportDetails().stream()
//                    .map(this::toFacilityReportDetailEntity)
//                    .collect(Collectors.toList());
//            facilityReport.setFacilityReportDetails(details);
//        }
//
//        return facilityReport;
//    }


    // Mapping from FacilityReportDTO to FacilityReport Entity
    public FacilityReport toFacilityReportEntity(FacilityReportDTO dto) {
        FacilityReport facilityReport = new FacilityReport();
        facilityReport.setId(dto.getId());

        // Set the facility entity based on the facilityId in the DTO
        Facility facility = facilityService.getFacilityById(dto.getFacilityId())
                .orElseThrow(() -> new RuntimeException("Facility not found"));
        facilityReport.setFacility(facility);

        facilityReport.setEmployeeID(dto.getEmployeeID());
        facilityReport.setTitle(dto.getTitle());
        facilityReport.setDescription(dto.getDescription());
        facilityReport.setStatus(dto.getStatus());

        // Map facility report details without setting the facilityReportId
        if (dto.getFacilityReportDetails() != null) {
            List<FacilityReportDetail> details = dto.getFacilityReportDetails().stream()
                    .map(this::toFacilityReportDetailEntity)
                    .collect(Collectors.toList());
            facilityReport.setFacilityReportDetails(details);

            // Set the parent facility report for each detail
            details.forEach(detail -> detail.setFacilityReport(facilityReport));
        }

        return facilityReport;
    }

    // Mapping between FacilityReportDetail and FacilityReportDetailDTO
    public FacilityReportDetailDTO toFacilityReportDetailDTO(FacilityReportDetail facilityReportDetail) {
        FacilityReportDetailDTO dto = new FacilityReportDetailDTO();
        dto.setId(facilityReportDetail.getId());
        dto.setComment(facilityReportDetail.getComment());
        dto.setCreateDate(facilityReportDetail.getCreateDate());
        dto.setLastModificationDate(facilityReportDetail.getLastModificationDate());
        dto.setEmployeeID(facilityReportDetail.getEmployeeID());
        dto.setFacilityReportId(facilityReportDetail.getFacilityReport().getId());
        return dto;
    }

    public FacilityReportDetail toFacilityReportDetailEntity(FacilityReportDetailDTO dto) {
        FacilityReportDetail facilityReportDetail = new FacilityReportDetail();
        facilityReportDetail.setId(dto.getId());
        facilityReportDetail.setComment(dto.getComment());
        facilityReportDetail.setCreateDate(dto.getCreateDate());
        facilityReportDetail.setLastModificationDate(dto.getLastModificationDate());
        facilityReportDetail.setEmployeeID(dto.getEmployeeID());

        // Ensure facilityReportId is not null
        if (dto.getFacilityReportId() == null) {
            throw new IllegalArgumentException("FacilityReportId cannot be null");
        }

        // Fetch the FacilityReport entity using the facilityReportId from DTO and set it
        FacilityReport facilityReport = facilityReportService.getFacilityReportById(dto.getFacilityReportId())
                .orElseThrow(() -> new RuntimeException("FacilityReport not found"));
        facilityReportDetail.setFacilityReport(facilityReport); // Set the FacilityReport

        return facilityReportDetail;
    }


    // Mapping between Landlord and LandlordDTO
    public LandlordDTO toLandlordDTO(Landlord landlord) {
        LandlordDTO dto = new LandlordDTO();
        dto.setId(landlord.getId());
        dto.setFirstName(landlord.getFirstName());
        dto.setLastName(landlord.getLastName());
        dto.setEmail(landlord.getEmail());
        dto.setCellPhone(landlord.getCellPhone());
        dto.setCreateDate(landlord.getCreateDate());
        dto.setLastModificationDate(landlord.getLastModificationDate());
        return dto;
    }

    public Landlord toLandlordEntity(LandlordDTO dto) {
        Landlord landlord = new Landlord();
        landlord.setId(dto.getId());
        landlord.setFirstName(dto.getFirstName());
        landlord.setLastName(dto.getLastName());
        landlord.setEmail(dto.getEmail());
        landlord.setCellPhone(dto.getCellPhone());
        return landlord;
    }
}
package com.housingservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HouseDTO {
    private Integer id;
    private Integer landlordId; // To represent the relationship with Landlord entity
    private String address;
    private Integer maxOccupant;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime lastModificationDate;
    private List<FacilityDTO> facilities; // To include associated facilities
    private List<Integer> employeeIds;
}

package com.housingservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FacilityReportDTO {
    private Integer id;
    private Integer facilityId; // To represent the relationship with Facility entity
    private Integer employeeID;
    private String title;
    private String description;
    private String status;
    private LocalDateTime createDate;
    private LocalDateTime lastModificationDate;
    private List<FacilityReportDetailDTO> facilityReportDetails; // To include associated report details
}

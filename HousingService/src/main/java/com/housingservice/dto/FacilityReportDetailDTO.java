package com.housingservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class FacilityReportDetailDTO {
    private Integer id;
    private String comment;
    private LocalDateTime createDate;
    private LocalDateTime lastModificationDate;
    private String employeeID;
    private String employeeName;
    private Integer facilityReportId;
}

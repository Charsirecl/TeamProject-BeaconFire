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
    private Integer landlordId;
    private String address;
    private Integer maxOccupant;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime lastModificationDate;
    private List<FacilityDTO> facilities;
    private List<String> employeeUsernames;
    private List<String> employeeIds;

    public List<String> getEmployeeNames() {
        return employeeUsernames;
    }

    public void setEmployeeNames(List<String> employeeNames) {
        this.employeeUsernames = employeeNames;
    }

    public void setEmployeeIds(List<String> employeeIds) {
        this.employeeIds = employeeIds;
    }

}

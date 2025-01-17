package com.composite.visastatuscompositeservice.dto.EmployeeService;

import lombok.Data;

@Data
public class VisaStatus {

    private String id;
    private String visaType;
    private Boolean activeFlag;
    private String startDate;
    private String endDate;
    private String lastModificationDate;
}

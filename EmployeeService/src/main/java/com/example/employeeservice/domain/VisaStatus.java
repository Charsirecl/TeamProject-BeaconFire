package com.example.employeeservice.domain;

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

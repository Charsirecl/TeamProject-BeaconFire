package com.example.employeeservice.domain;

import lombok.Data;

@Data
public class EmployeeVisaStatusResponse {
    private String fullName;
    private String visaType;
    private String expirationDate;
    private long daysLeft;

    public EmployeeVisaStatusResponse(String fullName, String visaType, String expirationDate, long daysLeft) {
        this.fullName = fullName;
        this.visaType = visaType;
        this.expirationDate = expirationDate;
        this.daysLeft = daysLeft;
    }
}

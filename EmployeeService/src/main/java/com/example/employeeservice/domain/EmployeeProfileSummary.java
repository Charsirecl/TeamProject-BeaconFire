package com.example.employeeservice.domain;

import lombok.Data;

@Data
public class EmployeeProfileSummary {
    private String fullName;
    private String ssn;
    private String workAuthorizationTitle;
    private String phoneNumber;
    private String email;

    public EmployeeProfileSummary(String fullName, String ssn, String workAuthorizationTitle, String phoneNumber, String email) {
        this.fullName = fullName;
        this.ssn = ssn;
        this.workAuthorizationTitle = workAuthorizationTitle;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
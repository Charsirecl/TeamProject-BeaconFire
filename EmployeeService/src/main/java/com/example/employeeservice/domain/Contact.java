package com.example.employeeservice.domain;

import lombok.Data;

@Data
public class Contact {
    private String id;
    private String firstName;
    private String lastName;
    private String cellPhone;
    private String alternatePhone;
    private String email;
    private String relationship;
    private String type;
}

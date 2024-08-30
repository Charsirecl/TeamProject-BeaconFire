package com.composite.visastatuscompositeservice.dto.EmployeeService;

import lombok.Data;

@Data
public class PersonalDocument {
    private String id;
    private String path;
    private String title;
    private String comment;
    private String createDate;
}

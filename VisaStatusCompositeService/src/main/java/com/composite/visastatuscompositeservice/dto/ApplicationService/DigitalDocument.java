package com.composite.visastatuscompositeservice.dto.ApplicationService;

import lombok.Data;

@Data
public class DigitalDocument {

    private Integer id;
    private String employeeId;
    private String type;
    private Boolean isRequired;
    private String path;
    private String description;
    private String title;
}

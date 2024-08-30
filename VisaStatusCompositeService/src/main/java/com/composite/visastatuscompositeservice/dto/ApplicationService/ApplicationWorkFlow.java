package com.composite.visastatuscompositeservice.dto.ApplicationService;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ApplicationWorkFlow {
    private Integer id;
    private int employeeID;
    private Timestamp createDate;
    private Timestamp lastModificationDate;
    private String status;
    private String comment;
}

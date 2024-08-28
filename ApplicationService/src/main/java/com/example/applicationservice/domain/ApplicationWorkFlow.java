package com.example.applicationservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data   // include @getter and @setter, etc. from lombok
@Entity
@Table(name = "ApplicationWorkFlow")
public class ApplicationWorkFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @Column(name = "EmployeeID")
    @JsonProperty("employee_id")
    private int employeeID;

    @Column(name = "CreateDate")
    @JsonProperty("create_date")
    private Timestamp createDate;

    @Column(name = "LastModificationDate")
    @JsonProperty("last_modification_date")
    private Timestamp lastModificationDate;

    @Column(name = "Status")
    @JsonProperty("status")
    private String status;

    @Column(name = "Comment")
    @JsonProperty("comment")
    private String comment;
}

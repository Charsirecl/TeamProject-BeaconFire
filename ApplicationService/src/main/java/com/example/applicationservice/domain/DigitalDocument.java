package com.example.applicationservice.domain;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
//import jakarta.persistence.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "DigitalDocument")
public class DigitalDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "EmployeeId")
    private String employeeId;

    @Column(name = "Type")
    private String type;

    @Column(name = "isRequired")
    private Boolean isRequired;

    @Column(name = "Path")
    private String path;

    @Column(name = "Description")
    private String description;

    @Column(name = "Title")
    private String title;
}

package com.example.employeeservice.domain;

import lombok.Data;

@Data
public class PersonalDocument {
    private String id;
    private String path;
    private String title;
    private String comment;
    private String createDate;
}

package com.example.employeeservice.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "Employee")
public class Employee {
    @Id
    @Field("_id")
    private ObjectId objectId;    // auto generated by mongoDB
    private String id;  // Employee ID
    private String userID;
    private String firstName;
    private String lastName;
    private String middleName;
    private String preferedName;
    private String email;
    private String cellPhone;
    private String alternatePhone;
    private String gender;
    private String ssn;
    private String dob;
    private String startDate;
    private String endDate;
    private String driverLicense;
    private String driverLicenseExpiration;
    private String houseID;
    private List<Contact> contact;
    private List<Address> address;
    private List<VisaStatus> visaStatus;
    private List<PersonalDocument> personalDocument;

    public Employee() {
        this.id = new ObjectId().toHexString(); // Automatically generate Employee ID
    }

    public String getLegalFullName() {
        return String.format("%s %s %s", firstName, middleName != null ? middleName : "", lastName);
    }

}


package com.housingservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "FacilityReportDetail")
public class FacilityReportDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FacilityReportID", nullable = false)
    private FacilityReport facilityReport;

    @Column(name = "EmployeeID", nullable = false)
    private Integer employeeID;

    @Column(name = "Comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "CreateDate", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "LastModificationDate", nullable = false)
    private LocalDateTime lastModificationDate;

    // Constructors
    public FacilityReportDetail() {
    }

    public FacilityReportDetail(FacilityReport facilityReport, Integer employeeID, String comment) {
        this.facilityReport = facilityReport;
        this.employeeID = employeeID;
        this.comment = comment;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FacilityReport getFacilityReport() {
        return facilityReport;
    }

    public void setFacilityReport(FacilityReport facilityReport) {
        this.facilityReport = facilityReport;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // JPA lifecycle callbacks
    @PrePersist
    protected void onCreate() {
        createDate = LocalDateTime.now();
        lastModificationDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastModificationDate = LocalDateTime.now();
    }
}

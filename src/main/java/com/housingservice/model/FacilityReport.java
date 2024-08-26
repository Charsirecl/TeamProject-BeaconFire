package com.housingservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "FacilityReport")
public class FacilityReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FacilityID", nullable = false)
    private Facility facility;

    @Column(name = "EmployeeID", nullable = false)
    private Integer employeeID;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "Status")
    private String status;

    @OneToMany(mappedBy = "facilityReport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FacilityReportDetail> facilityReportDetails;

    @Column(name = "CreateDate", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "LastModificationDate", nullable = false)
    private LocalDateTime lastModificationDate;

    // Constructors
    public FacilityReport() {
    }

    public FacilityReport(Facility facility, Integer employeeID, String title, String description, String status) {
        this.facility = facility;
        this.employeeID = employeeID;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<FacilityReportDetail> getFacilityReportDetails() {
        return facilityReportDetails;
    }

    public void setFacilityReportDetails(List<FacilityReportDetail> facilityReportDetails) {
        this.facilityReportDetails = facilityReportDetails;
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

package com.housingservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "facility_report")
public class FacilityReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facilityid", nullable = false)
    @JsonManagedReference
    private Facility facility;

    @Column(name = "employeeid", nullable = false)
    private String employeeID;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "facilityReport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FacilityReportDetail> facilityReportDetails;

    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "last_modification_date", nullable = false)
    private LocalDateTime lastModificationDate;

    public FacilityReport(Facility facility, String employeeID, String title, String description, String status) {
        this.facility = facility;
        this.employeeID = employeeID;
        this.title = title;
        this.description = description;
        this.status = status;
    }

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

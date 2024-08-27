package com.housingservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "facility_report_detail")
@Getter
@Setter
@NoArgsConstructor
public class FacilityReportDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "last_modification_date", nullable = false)
    private LocalDateTime lastModificationDate;

    @Column(name = "employeeid", nullable = false)
    private Integer employeeID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_reportid", nullable = false)
    private FacilityReport facilityReport;

    // Constructor with fields
    public FacilityReportDetail(FacilityReport facilityReport, Integer employeeID, String comment) {
        this.facilityReport = facilityReport;
        this.employeeID = employeeID;
        this.comment = comment;
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

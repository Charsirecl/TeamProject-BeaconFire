package com.housingservice.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "house")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "landlordid", nullable = false)
    private Landlord landlord;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "max_occupant", nullable = false)
    private Integer maxOccupant;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Facility> facilities;

    @Column(name = "create_date", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "last_modification_date", nullable = false)
    private LocalDateTime lastModificationDate;

    // No-argument constructor
    public House() {
    }

    // Constructor with fields
    public House(Landlord landlord, String address, Integer maxOccupant, String description) {
        this.landlord = landlord;
        this.address = address;
        this.maxOccupant = maxOccupant;
        this.description = description;
    }

    // Custom setter for landlordId
    public void setLandlordId(Integer landlordId) {
        this.landlord = new Landlord();
        this.landlord.setId(landlordId);
    }

    // Getter for landlordId for compatibility
    public Integer getLandlordId() {
        return this.landlord != null ? this.landlord.getId() : null;
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

package com.housingservice.model;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "House")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LandlordID", nullable = false)
    private Landlord landlord;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "MaxOccupant", nullable = false)
    private Integer maxOccupant;

    @Column(name = "Description", columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Facility> facilities;

    // New field to store roommate IDs
    @ElementCollection // This annotation specifies a collection of basic types
    @CollectionTable(name = "RoommateIds", joinColumns = @JoinColumn(name = "HouseID"))
    @Column(name = "RoommateId")
    private List<Integer> roommateIds;

    @Column(name = "CreateDate", nullable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "LastModificationDate", nullable = false)
    private LocalDateTime lastModificationDate;

    // No-argument constructor
    public House() {
    }

    public House(Landlord landlord, String address, Integer maxOccupant, String description, List<Integer> roommateIds) {
        this.landlord = landlord;
        this.address = address;
        this.maxOccupant = maxOccupant;
        this.description = description;
        this.roommateIds = roommateIds;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Landlord getLandlord() {
        return landlord;
    }

    public void setLandlord(Landlord landlord) {
        this.landlord = landlord;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getMaxOccupant() {
        return maxOccupant;
    }

    public void setMaxOccupant(Integer maxOccupant) {
        this.maxOccupant = maxOccupant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(List<Facility> facilities) {
        this.facilities = facilities;
    }

    public List<Integer> getRoommateIds() {
        return roommateIds;
    }

    public void setRoommateIds(List<Integer> roommateIds) {
        this.roommateIds = roommateIds;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getLastModificationDate() {
        return lastModificationDate;
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

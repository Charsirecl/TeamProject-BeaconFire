package com.az.authenticationservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false,unique = true)
    private String rolename;

    @Column(nullable = false)
    private String roledescription;

    @Column(nullable = false)
    private LocalDateTime createdate;

    @Column(nullable = false)
    private LocalDateTime lastmodificationdate;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "roles")
    //@JsonBackReference
    @ToString.Exclude
    private Set<User> users;

    @PrePersist
    protected void onCreate() {
        this.createdate = LocalDateTime.now();
        this.lastmodificationdate = this.createdate;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastmodificationdate = LocalDateTime.now();
    }
}

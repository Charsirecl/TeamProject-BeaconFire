package com.az.authenticationservice.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "userrole")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private boolean activeflag = true;

    @Column(nullable = false)
    private LocalDateTime createdate;

    @Column(nullable = false)
    private LocalDateTime lastmodificationdate;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

    @PrePersist
    protected void onCreate() {
        this.activeflag = true;  // Default to 'Y' (true)
        this.createdate = LocalDateTime.now();
        this.lastmodificationdate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastmodificationdate = LocalDateTime.now();
    }

}

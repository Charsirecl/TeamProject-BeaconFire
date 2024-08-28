package com.az.authenticationservice.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false,unique = true)
    private String password;

    @Column(nullable = false)
    private LocalDateTime createdate;

    @Column(nullable = false)
    private boolean activateflag;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "userrole",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
//    @JsonManagedReference
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<RegistrationToken> tokens = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        this.createdate = LocalDateTime.now();
    }

}

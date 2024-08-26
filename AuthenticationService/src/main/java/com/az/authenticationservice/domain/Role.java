package com.az.authenticationservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}

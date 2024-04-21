package org.aston.registrationservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "ADDRESS")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;


}

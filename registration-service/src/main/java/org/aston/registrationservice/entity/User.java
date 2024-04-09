package org.aston.registrationservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
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
    @NotEmpty(message = "Enter username!")
    @Size(min = 3, max = 15, message = "Username must be from 3 to 15 characters!")
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;
    @NotEmpty(message = "Enter password!")
    @Size(min = 3, max = 70, message = "Password must be from 3 to 70 characters!!")
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @NotEmpty(message = "Email should not be empty!")
    @Email(message = "Email should be valid!")
    @Column(name = "EMAIL", unique = true)
    private String email;
    @Column(name = "ADDRESS")
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User user)) return false;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getAddress(), user.getAddress()) && getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getEmail(), getAddress(), getRole());
    }


}

package org.aston.registrationservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.aston.registrationservice.entity.Role;

public record UserDto(Long id,
                      @NotEmpty(message = "Enter username!")
                      @Size(min = 3, max = 15, message = "Username must be from 3 to 15 characters!")
                      String username,
                      @NotEmpty(message = "Enter password!")
                      @Size(min = 3, max = 70, message = "Password must be from 3 to 70 characters!!")

                      String password,
                      @NotEmpty(message = "Email should not be empty!")
                      @Email(message = "Email should be valid!")
                      String email,
                      String address,
                      Role role) {

}

package org.aston.registrationservice.dto;

import org.aston.registrationservice.entity.Role;

public record UserDto(Long id,
                      String username,
                      String password,
                      String email,
                      String address,
                      Role role) {

}

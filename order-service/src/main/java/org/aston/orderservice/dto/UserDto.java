package org.aston.orderservice.dto;

import lombok.Builder;
import org.aston.orderservice.enums.Role;

@Builder
public record UserDto(Long id,
                      String username,
                      String password,
                      String email,
                      String address,
                      Role role) {
}

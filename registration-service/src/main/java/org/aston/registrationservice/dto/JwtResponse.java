package org.aston.registrationservice.dto;

import org.aston.registrationservice.entity.Role;

public record JwtResponse(String token,
                          String username

) {
}

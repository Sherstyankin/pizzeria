package org.aston.registrationservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

//@EqualsAndHashCode(callSuper = true)
//@Data
public record JwtRequest(String username, String password) {

}

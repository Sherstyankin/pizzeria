package org.aston.registrationservice.dto;

import org.aston.registrationservice.entity.User;

public record ResponsePizzaDto(Long id,
                               String pizzaName,
                               Long count,
                               User user) {
}

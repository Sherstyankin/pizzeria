package org.aston.registrationservice.dto;

public record PizzaDto(Long id,
                       String pizzaName,
                       Long count,
                       Long userId) {
}

package org.aston.kitchenservice.dto.pizzaDto;

import lombok.Builder;

@Builder
public record PizzaDto(
        Long id,
        String name,
        String description
) {
}

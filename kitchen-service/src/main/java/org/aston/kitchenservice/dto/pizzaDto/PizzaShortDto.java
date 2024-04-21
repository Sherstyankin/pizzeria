package org.aston.kitchenservice.dto.pizzaDto;

import lombok.Builder;

@Builder
public record PizzaShortDto(
        String name,
        String description
) {
}

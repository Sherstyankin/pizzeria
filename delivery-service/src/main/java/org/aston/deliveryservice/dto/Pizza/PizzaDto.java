package org.aston.deliveryservice.dto.Pizza;

import lombok.Builder;

@Builder
public record PizzaDto(Long id,
                       String pizzaName,
                       Long count,
                       Long userId) {
}

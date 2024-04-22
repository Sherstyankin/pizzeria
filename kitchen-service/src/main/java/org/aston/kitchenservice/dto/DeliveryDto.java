package org.aston.kitchenservice.dto;

import lombok.Builder;
import org.aston.kitchenservice.entity.Pizza;

import java.util.List;

@Builder
public record DeliveryDto(
        Long orderId,
        List<Pizza> pizzas
) {
}

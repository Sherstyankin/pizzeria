package org.aston.orderservice.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record KitchenDto(Long orderId,
                         List<Long> pizzaIds) {
}

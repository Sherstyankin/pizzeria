package org.aston.orderservice.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ResponseOrderDto(Long id,
                               Long userId,
                               List<Long> pizzaIds) {
}

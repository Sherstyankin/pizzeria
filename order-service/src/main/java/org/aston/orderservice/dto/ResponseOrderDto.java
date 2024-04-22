package org.aston.orderservice.dto;

import lombok.Builder;
import org.aston.orderservice.enums.OrderStatus;

import java.util.List;

@Builder
public record ResponseOrderDto(Long id,
                               Long userId,
                               List<Long> pizzaIds,
                               OrderStatus status) {
}

package org.aston.kitchenservice.dto;

import java.util.List;

public record OrderDto(
        Long orderId,
        List<Long> pizzasId
) {
}

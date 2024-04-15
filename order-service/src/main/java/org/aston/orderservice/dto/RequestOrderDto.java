package org.aston.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;


@Builder
public record RequestOrderDto(@NotNull Long userId,
                              @NotNull List<Long> pizzaIds) {
}


package org.aston.deliveryservice.dto.User;

import lombok.Builder;
import org.aston.deliveryservice.entity.Pizza;

import java.util.List;

@Builder
public record UserDto(long userId, List<Pizza> pizzas) {
}

package org.aston.deliveryservice.dto.User;

import java.util.List;
import org.aston.deliveryservice.entity.Pizza;
import lombok.Builder;

@Builder
public record UserDto(long userId, List<Pizza> pizzas) {}

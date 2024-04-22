package org.aston.deliveryservice.dto.Kitchen;

import lombok.Builder;
import org.aston.deliveryservice.entity.Pizza;

import java.util.List;

@Builder
public record KitchenDto(Long orderId, List<Pizza> pizzas) {
}
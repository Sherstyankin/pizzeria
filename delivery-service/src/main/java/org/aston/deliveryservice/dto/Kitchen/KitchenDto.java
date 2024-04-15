package org.aston.deliveryservice.dto.Kitchen;

import java.util.List;
import org.aston.deliveryservice.entity.Pizza;
import lombok.Builder;

@Builder
public record KitchenDto(long orderId, List<Pizza> pizzas) {}
package org.aston.deliveryservice.dto;

import java.util.List;

import org.aston.deliveryservice.entity.Pizza;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KitchenDto {
    private long orderId;
    private List<Pizza> pizzas;
}

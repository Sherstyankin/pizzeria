package org.aston.kitchenservice.service;

import org.aston.kitchenservice.dto.DeliveryDto;
import org.aston.kitchenservice.dto.OrderDto;
import org.aston.kitchenservice.dto.pizzaDto.PizzaDto;

import java.util.List;

public interface PizzaService {
    PizzaDto create(PizzaDto pizzaDto);

    List<PizzaDto> getAll();

    PizzaDto getById(long id);

    DeliveryDto makePizza(OrderDto orderDto);
}

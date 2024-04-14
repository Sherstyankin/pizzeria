package org.aston.registrationservice.service;

import org.aston.registrationservice.dto.PizzaDto;

import java.util.List;

public interface PizzaService {
    PizzaDto getPizzaById(Long id);
    //DeliveredOrdersDto findDeliveredOrderByUserId(Long userId, Long orderId);
    List<PizzaDto> findAllPizzaByUserId (Long UserId);
    List<PizzaDto> getAllPizzas();
    PizzaDto saveNewPizza(PizzaDto pizzaDto);
    PizzaDto updatePizza(Long id, PizzaDto pizzaDto);
    void deletePizza(Long id);
}

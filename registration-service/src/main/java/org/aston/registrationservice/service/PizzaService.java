package org.aston.registrationservice.service;

import org.aston.registrationservice.dto.PizzaDto;

import java.util.List;
import java.util.Optional;

public interface PizzaService {
    PizzaDto getPizzaById(Long id);
    List<PizzaDto> findAllByUser_Id (Long UserId);
    List<PizzaDto> getAllPizzas();
    PizzaDto saveNewPizza(PizzaDto pizzaDto);
    PizzaDto updatePizza(Long id, PizzaDto pizzaDto);
    void deletePizza(Long id);
}

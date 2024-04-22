package org.aston.registrationservice.service;

import org.aston.registrationservice.dto.PizzaDto;
import org.aston.registrationservice.dto.ResponsePizzaDto;

import java.util.List;

public interface PizzaService {

    PizzaDto getPizzaById(Long id);

    List<PizzaDto> getAllPizzas();

    void saveNewPizza(List<PizzaDto> pizzaDtos);

    List<PizzaDto> updatePizza(Long userId, List<PizzaDto> pizzaDto);

    List<ResponsePizzaDto> getPizzasByUserId(Long userId);

}

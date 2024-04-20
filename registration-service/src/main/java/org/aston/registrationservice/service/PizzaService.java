package org.aston.registrationservice.service;

import org.aston.registrationservice.dto.PizzaDto;

import java.util.List;
import java.util.Optional;

public interface PizzaService {
    PizzaDto getPizzaById(Long id);

    List<PizzaDto> getAllPizzas();


    List<PizzaDto> saveNewPizza(Long userId, List<PizzaDto> pizzaDtos);


    List <PizzaDto> updatePizza(Long userId, List<PizzaDto> pizzaDto);


}

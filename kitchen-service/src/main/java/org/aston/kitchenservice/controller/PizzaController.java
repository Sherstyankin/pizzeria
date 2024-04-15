package org.aston.kitchenservice.controller;

import lombok.RequiredArgsConstructor;
import org.aston.kitchenservice.dto.OrderDto;
import org.aston.kitchenservice.dto.PizzaDto;
import org.aston.kitchenservice.service.PizzaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/kitchen")
public class PizzaController {

    private final PizzaService pizzaService;

    @PostMapping
    public PizzaDto create(@RequestBody PizzaDto pizzaDto) {
        return pizzaService.create(pizzaDto);
    }

    @GetMapping(value = "/pizza/all")
    public List<PizzaDto> getAllPizzas() {
        return pizzaService.getAll();
    }

    @GetMapping(value = "/pizza/{id}")
    public PizzaDto getById(@PathVariable(value = "id") long id) {
        return pizzaService.getById(id);
    }

    @PostMapping(value = "/makepizza")
    public void makePizza(@RequestBody OrderDto orderDto) {
        pizzaService.makePizza(orderDto);
    }
}

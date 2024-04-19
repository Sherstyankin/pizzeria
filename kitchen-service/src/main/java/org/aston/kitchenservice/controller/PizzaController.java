package org.aston.kitchenservice.controller;

import lombok.RequiredArgsConstructor;
import org.aston.kitchenservice.dto.DeliveryDto;
import org.aston.kitchenservice.dto.OrderDto;
import org.aston.kitchenservice.dto.pizzaDto.PizzaDto;
import org.aston.kitchenservice.dto.pizzaDto.PizzaShortDto;
import org.aston.kitchenservice.mapper.PizzaMapper;
import org.aston.kitchenservice.service.PizzaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/kitchen")
public class PizzaController {

    private final PizzaService pizzaService;

    @PostMapping
    public PizzaShortDto create(@RequestBody PizzaDto pizzaDto) {

        pizzaService.create(pizzaDto);

        return PizzaMapper.INSTANCE.toPizzaShortDto(pizzaDto);
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
    public DeliveryDto makePizza(@RequestBody OrderDto orderDto) {

        return pizzaService.makePizza(orderDto);
    }
}

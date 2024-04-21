package org.aston.kitchenservice.service;

import lombok.AllArgsConstructor;
import org.aston.kitchenservice.client.DeliveryClient;
import org.aston.kitchenservice.dto.DeliveryDto;
import org.aston.kitchenservice.dto.OrderDto;
import org.aston.kitchenservice.dto.pizzaDto.PizzaDto;
import org.aston.kitchenservice.entity.Pizza;
import org.aston.kitchenservice.exception.pizza.PizzaNotFoundException;
import org.aston.kitchenservice.mapper.PizzaMapper;
import org.aston.kitchenservice.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PizzaServiceImpl implements PizzaService {

    private final PizzaRepository pizzaRepository;
    private final DeliveryClient deliveryClient;

    @Override
    public PizzaDto create(PizzaDto pizzaDto) {

        pizzaRepository.save(PizzaMapper.INSTANCE.toPizza(pizzaDto));
        return pizzaDto;
    }

    @Override
    public List<PizzaDto> getAll() {

        return pizzaRepository.findAll()
                .stream()
                .map(PizzaMapper.INSTANCE::toPizzaDto)
                .collect(Collectors.toList());
    }

    @Override
    public PizzaDto getById(long id) {

        return PizzaMapper.INSTANCE.toPizzaDto(getPizza(id));
    }

    @Override
    public DeliveryDto makePizza(OrderDto orderDto) {

        List<Pizza> pizzas = orderDto.pizzaIds().stream()
                .map(this::getPizza).toList();


        DeliveryDto deliveryDto = DeliveryDto.builder()
                .pizzaList(pizzas)
                .orderId(orderDto.orderId())
                .build();

        //deliveryClient.sendDeliveryDto(deliveryDto);

        return deliveryDto;
    }

    private Pizza getPizza(long id) {

        return pizzaRepository.findById(id)
                .orElseThrow(() -> new PizzaNotFoundException(id));
    }
}

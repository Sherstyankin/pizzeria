package org.aston.registrationservice.service;

import lombok.RequiredArgsConstructor;
import org.aston.registrationservice.dto.PizzaDto;
import org.aston.registrationservice.dto.ResponsePizzaDto;
import org.aston.registrationservice.entity.Pizza;
import org.aston.registrationservice.entity.User;
import org.aston.registrationservice.exceptions.ObjectNotFoundException;
import org.aston.registrationservice.mapper.PizzaMapper;
import org.aston.registrationservice.repository.PizzaRepository;
import org.aston.registrationservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaMapper pizzaMapper;
    private final UserRepository userRepository;


    @Override
    public PizzaDto getPizzaById(Long id) {
        validateDeliveredOrderById(id);
        Optional<Pizza> order = pizzaRepository.findById(id);
        return order.map(pizzaMapper::toPizzaDto).orElse(null);

    }


    @Override
    public List<PizzaDto> getAllPizzas() {
        List<Pizza> orders = pizzaRepository.findAll();
        return orders.stream().map(pizzaMapper::toPizzaDto).collect(Collectors.toList());

    }


    @Override
    public void saveNewPizza(List<PizzaDto> pizzaDtos) {
        User user = userRepository.findById(pizzaDtos.get(0).userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + pizzaDtos.get(0).userId()));

        //List<Pizza> pizzas = new ArrayList<>();
        List<Pizza> pizzas = pizzaDtos.stream()
                .map(dto -> pizzaMapper.toPizza(dto))
                .peek(dto -> dto.setUser(user))
                .toList();
        pizzaRepository.saveAll(pizzas);
    /*for (PizzaDto pizzaDto : pizzaDtos) {
        List<Pizza> existingPizzas = pizzaRepository.findByUserIdAndPizzaName(userId, pizzaDto.pizzaName());
        if (!existingPizzas.isEmpty()) {
            Pizza existingPizza = existingPizzas.get(0);
            existingPizza.setCount(existingPizza.getCount() + pizzaDto.count()); // Увеличиваем количество пиццы
            // Обновляем существующую пиццу в базе данных
            pizzaRepository.save(existingPizza);
        } else {
            Pizza pizza = pizzaMapper.toPizza(pizzaDto);
            pizza.setUser(user);
            pizzas.add(pizza);
        }
    }

    // Сохраняем новые пиццы
    List<Pizza> savedPizzas =

    // Обновляем список pizzaDtos с обновленными и новыми пиццами
    List<PizzaDto> updatedPizzaDtos = Stream.concat(savedPizzas.stream().map(pizzaMapper::toPizzaDto), pizzaDtos.stream())
            .collect(Collectors.toList());

    return updatedPizzaDtos;*/
    }


    @Override
    public List<PizzaDto> updatePizza(Long id, List<PizzaDto> pizzaDtos) {
        List<PizzaDto> updatedPizzas = new ArrayList<>();
        for (PizzaDto pizzaDto : pizzaDtos) {
            Pizza pizza = pizzaRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Pizza not found with ID: " + id));

            pizza.setCount(pizzaDto.count());

            pizza = pizzaRepository.save(pizza);

            updatedPizzas.add(pizzaMapper.toPizzaDto(pizza));
        }

        return updatedPizzas;
    }

    @Override
    public List<ResponsePizzaDto> getPizzasByUserId(Long userId) {
        List<Pizza> pizzas = pizzaRepository.findByUserId(userId);
        return pizzas.stream()
                .map(pizzaMapper::toResponsePizzaDto)
                .collect(Collectors.toList());
    }

    private void validateDeliveredOrderById(Long id) {
        if (!pizzaRepository.existsById(id)) {
            throw new ObjectNotFoundException("Pizza with id = " + id + " doesn't exist.");
        }
    }

}

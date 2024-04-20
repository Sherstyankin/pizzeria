package org.aston.registrationservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aston.registrationservice.dto.PizzaDto;
import org.aston.registrationservice.entity.Pizza;
import org.aston.registrationservice.entity.User;
import org.aston.registrationservice.exceptions.ObjectNotFoundException;
import org.aston.registrationservice.mapper.PizzaMapper;
import org.aston.registrationservice.repository.PizzaRepository;
import org.aston.registrationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return orders.stream()
                .map(pizza -> new PizzaDto(pizza.getId(), pizza.getPizza_name(), pizza.getCount(), pizza.getUser()))
                .collect(Collectors.toList());
    }


    public List<PizzaDto> saveNewPizza(Long userId, List<PizzaDto> pizzaDtos) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        List<Pizza> pizzas = new ArrayList<>();
        for (PizzaDto pizzaDto : pizzaDtos) {
            Pizza pizza = new Pizza();
            pizza.setId(pizzaDto.id());
            pizza.setPizza_name(pizzaDto.pizza_name());
            pizza.setCount(pizzaDto.count());
            pizza.setUser(user);
            pizzas.add(pizza);
        }

        List<Pizza> savedPizzas = pizzaRepository.saveAll(pizzas);

        return savedPizzas.stream()
                .map(pizza -> new PizzaDto(pizza.getId(), pizza.getPizza_name(), pizza.getCount(), pizza.getUser()))
                .collect(Collectors.toList());
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



    private void validateDeliveredOrderById(Long id) {
        if (!pizzaRepository.existsById(id)) {
            throw new ObjectNotFoundException("Pizza with id = " + id + " doesn't exist.");
        }
    }

}

package org.aston.registrationservice.service;

import org.aston.registrationservice.dto.PizzaDto;
import org.aston.registrationservice.entity.Pizza;
import org.aston.registrationservice.exceptions.ObjectNotFoundException;
import org.aston.registrationservice.mapper.PizzaMapper;
import org.aston.registrationservice.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaMapper pizzaMapper;
@Autowired
    public PizzaServiceImpl(PizzaRepository pizzaRepository, PizzaMapper pizzaMapper) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaMapper = pizzaMapper;
    }

    @Override
    public PizzaDto getPizzaById(Long id) {
        validateDeliveredOrderById(id);
        Optional<Pizza> order = pizzaRepository.findById(id);
        return order.map(pizzaMapper::toPizzaDto).orElse(null);
    }
    @Override
    public List<PizzaDto> findAllPizzaByUserId (Long userId){

        List<Pizza> pizzas = pizzaRepository.findAllPizzaByUserId(userId);
        return pizzas.stream()
                .map(pizza -> new PizzaDto(pizza.getId(), pizza.getPizza_name(), pizza.getCount(), pizza.getUser()))
                .collect(Collectors.toList());
    }



    @Override
    public List<PizzaDto> getAllPizzas() {
        List<Pizza> orders = pizzaRepository.findAll();
        return orders.stream().map(pizzaMapper::toPizzaDto).collect(Collectors.toList());
    }

    @Override
    public PizzaDto saveNewPizza(PizzaDto pizzaDto) {
        Pizza order = pizzaMapper.toPizza(pizzaDto);
        order = pizzaRepository.save(order);
        return pizzaMapper.toPizzaDto(order);
    }

    @Override
    public PizzaDto updatePizza(Long id, PizzaDto pizzaDto) {
        validateDeliveredOrderById(id);
        Pizza order = pizzaRepository.findById(id).get();

        if (pizzaDto != null) {
            order.setCount(pizzaDto.count());
        }
        order = pizzaRepository.save(order);
        return pizzaMapper.toPizzaDto(order);

    }

    @Override
    public void deletePizza(Long id) {
        validateDeliveredOrderById(id);
        pizzaRepository.deleteById(id);
    }

    private void validateDeliveredOrderById(Long id) {
        if (!pizzaRepository.existsById(id)) {
            throw new ObjectNotFoundException("Pizza with id = " + id + " doesn't exist.");
        }
    }
}

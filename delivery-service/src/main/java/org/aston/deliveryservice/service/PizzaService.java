package org.aston.deliveryservice.service;

import java.util.List;
import org.aston.deliveryservice.entity.Courier;
import org.aston.deliveryservice.entity.Pizza;
import org.aston.deliveryservice.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PizzaService {

    private PizzaRepository repository;
    
    public void link(Courier courier, long orderId, List<Pizza> pizzas) {
        for (Pizza pizza : pizzas) {
            pizza.setCourier(courier);
            pizza.setOrderId(orderId);
        }
        repository.saveAll(pizzas);
    }
}

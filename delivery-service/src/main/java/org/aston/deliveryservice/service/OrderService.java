package org.aston.deliveryservice.service;

import java.util.List;

import org.aston.deliveryservice.client.OrderClient;
import org.aston.deliveryservice.entity.Courier;
import org.aston.deliveryservice.entity.Pizza;
import org.aston.deliveryservice.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private CourierService courierService;

    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private OrderClient orderClient;

    public void makeOrder(long orderId, List<Pizza> pizzas) {
        List<Courier> freeCouriers = courierService.getFreeCouriers();
        Courier courier = freeCouriers.get(0);

        for(Pizza pizza : pizzas) {
            pizza.setCourier(courier);
            pizza.setOrderId(orderId);
        }

        pizzaRepository.saveAll(pizzas);
    }
}

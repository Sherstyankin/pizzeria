package org.aston.deliveryservice.controller;

import org.aston.deliveryservice.dto.KitchenDto;
import org.aston.deliveryservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KitchenController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/delivery/sendPizza")
    public String makeOrder(@RequestBody KitchenDto kitchenDto) {
        orderService.makeOrder(kitchenDto.getOrderId(), kitchenDto.getPizzas());

        return "Доставка оформлена успешно. Пиццы уже в пути" ;
    }
}

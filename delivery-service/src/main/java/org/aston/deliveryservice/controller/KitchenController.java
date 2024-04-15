package org.aston.deliveryservice.controller;

import org.aston.deliveryservice.dto.Kitchen.KitchenDto;
import org.aston.deliveryservice.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class KitchenController {

    private OrderService orderService;

    @PostMapping("/delivery/sendPizza")
    public String makeOrder(@RequestBody KitchenDto kitchenDto) {
        orderService.makeOrder(kitchenDto);

        return "Доставка оформлена успешно. Пиццы уже в пути" ;
    }
}

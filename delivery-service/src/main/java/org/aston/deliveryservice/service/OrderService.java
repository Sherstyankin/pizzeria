package org.aston.deliveryservice.service;

import org.aston.deliveryservice.client.OrderServiceClient;
import org.aston.deliveryservice.client.UserServiceClient;
import org.aston.deliveryservice.constants.CourierStatus;
import org.aston.deliveryservice.dto.Order.OrderIdDto;
import org.aston.deliveryservice.dto.Kitchen.KitchenDto;
import org.aston.deliveryservice.dto.User.UserDto;
import org.aston.deliveryservice.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class OrderService {

    @Autowired
    private PizzaRepository pizzaRepository;

    private CourierService courierService;
    private PizzaService pizzaService;

    private OrderServiceClient orderClient;
    private UserServiceClient userClient;

    public void makeOrder(KitchenDto dto) {
        var courier = courierService.get();
        var orderId = dto.orderId();
        var pizzas = dto.pizzas();

        var orderIdDto = OrderIdDto.builder().orderId(orderId).build();

        courierService.changeStatus(courier, CourierStatus.IN_PROGRESS);        
        pizzaService.link(courier, orderId, pizzas);

        var userId = getUserIdFromOrderService(orderIdDto);
        var userPizza = pizzaRepository.getFromCurrentOrderId(orderId);

        var userDto = UserDto.builder().userId(userId).pizzas(userPizza).build();
        makeDelivery(userDto);
        changeStatus(orderIdDto);
    }

    private void makeDelivery(UserDto userDto) {
        userClient.sendPost(userDto);
    }

    private long getUserIdFromOrderService(OrderIdDto dto) {
        return orderClient.sendGet(dto).userId();
    }

    private void changeStatus(OrderIdDto dto) {
        orderClient.patchStatus(dto);
    }
}

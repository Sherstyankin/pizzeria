package org.aston.deliveryservice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aston.deliveryservice.client.OrderServiceClient;
import org.aston.deliveryservice.client.UserServiceClient;
import org.aston.deliveryservice.constants.CourierStatus;
import org.aston.deliveryservice.dto.Kitchen.KitchenDto;
import org.aston.deliveryservice.dto.Order.OrderIdDto;
import org.aston.deliveryservice.dto.Pizza.PizzaDto;
import org.aston.deliveryservice.entity.Pizza;
import org.aston.deliveryservice.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
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

        Long userId = getUserIdFromOrderService(orderIdDto);
        //var userPizzaList = pizzaRepository.getFromCurrentOrderId(orderId);
        List<Pizza> userPizzaList = dto.pizzas();
        List<PizzaDto> pizzaDtoList = userPizzaList.stream()
                .map(pizza -> PizzaDto.builder()
                        .id(pizza.getId())
                        .pizzaName(pizza.getName())
                        .count(pizza.getCount())
                        .userId(userId)
                        .build())
                .toList();

        //var userDto = UserDto.builder().userId(userId).pizzas(userPizza).build();
        makeDelivery(pizzaDtoList);
        changeStatus(orderIdDto);
    }

    private void makeDelivery(List<PizzaDto> pizzaDtoList) {
        userClient.sendPost(pizzaDtoList);
    }

    private Long getUserIdFromOrderService(OrderIdDto dto) {
        Long userId = orderClient.sendGet(dto.orderId());
        if (userId == null) {
            throw new EntityNotFoundException("id не получен");
        }
        return userId;
    }

    private void changeStatus(OrderIdDto dto) {
        orderClient.patchStatus(dto);
    }
}

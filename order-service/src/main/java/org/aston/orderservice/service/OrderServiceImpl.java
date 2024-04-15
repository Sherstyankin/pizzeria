package org.aston.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.aston.orderservice.client.KitchenClient;
import org.aston.orderservice.client.RegistrationClient;
import org.aston.orderservice.dto.KitchenDto;
import org.aston.orderservice.dto.RequestOrderDto;
import org.aston.orderservice.dto.ResponseOrderDto;
import org.aston.orderservice.entity.Order;
import org.aston.orderservice.enums.OrderStatus;
import org.aston.orderservice.exception.UserNotAuthenticatedException;
import org.aston.orderservice.mapper.OrderMapper;
import org.aston.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final KitchenClient kitchenClient;
    private final RegistrationClient registrationClient;

    @Override
    public ResponseOrderDto findOrderById(Long orderId) {
        Order order = findById(orderId);
        return OrderMapper.INSTANCE.mapToDto(order);
    }

    @Override
    public void postOrder(RequestOrderDto dto) {
        if (registrationClient.checkUser(dto.userId()) == null) {
            throw new UserNotAuthenticatedException("Пользователь не зарегистрирован или не вошел в аккуант");
        }
        Order order = OrderMapper.INSTANCE.mapToEntity(dto);
        repository.save(order);
        kitchenClient.sendKitchenDto(new KitchenDto(order.getId(), order.getPizzaIds()));
    }

    @Override
    public Long findUserIdByOrderId(Long orderId) {
        Order order = findById(orderId);
        return order.getUserId();
    }

    @Override
    public void changeOrderStatus(Long orderId) {
        Order order = findById(orderId);
        order.setStatus(OrderStatus.DELIVERED);
        repository.save(order);
    }

    private Order findById(Long orderId) {
        return repository.findById(orderId).orElseThrow();
    }
}

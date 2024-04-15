package org.aston.orderservice.service;

import org.aston.orderservice.dto.RequestOrderDto;
import org.aston.orderservice.dto.ResponseOrderDto;

public interface OrderService {

    ResponseOrderDto findOrderById(Long orderId);

    void postOrder(RequestOrderDto dto);

    Long findUserIdByOrderId(Long orderId);

    void changeOrderStatus(Long orderId);
}

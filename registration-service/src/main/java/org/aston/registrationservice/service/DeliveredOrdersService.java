package org.aston.registrationservice.service;

import org.aston.registrationservice.dto.DeliveredOrdersDto;

import java.util.List;

public interface DeliveredOrdersService {
    DeliveredOrdersDto getDeliveredOrderById(Long id);
    //DeliveredOrdersDto findDeliveredOrderByUserId(Long userId, Long orderId);
    List<DeliveredOrdersDto> findAllDeliveredOrdersByUserId (Long UserId);
    List<DeliveredOrdersDto> getAllDeliveredOrders();
    DeliveredOrdersDto saveNewDeliveredOrder(DeliveredOrdersDto deliveredOrdersDto);
    DeliveredOrdersDto updateDeliveredOrder(Long id, DeliveredOrdersDto deliveredOrdersDto);
    void deleteDeliveredOrder(Long id);
}

package org.aston.registrationservice.repository;

import org.aston.registrationservice.entity.DeliveredOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveredOrdersRepository extends JpaRepository<DeliveredOrders, Long> {
    List<DeliveredOrders> findAllDeliveredOrdersByUserId (Long userId);
}

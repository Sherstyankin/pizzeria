package org.aston.deliveryservice.repository;

import org.aston.deliveryservice.entity.Pizza;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {

    @Query(value = "SELECT * FROM pizzas WHERE order_id = :order_id", nativeQuery = true)
    List<Pizza> getFromCurrentOrderId(@Param("order_id") long orderId);
}

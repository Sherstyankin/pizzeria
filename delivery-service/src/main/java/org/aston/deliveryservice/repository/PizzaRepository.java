package org.aston.deliveryservice.repository;

import org.aston.deliveryservice.entity.Pizza;
import org.springframework.data.repository.CrudRepository;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {
    
}

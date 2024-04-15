package org.aston.kitchenservice.repository;

import org.aston.kitchenservice.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}

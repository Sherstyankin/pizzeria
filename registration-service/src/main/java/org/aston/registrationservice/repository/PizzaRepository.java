package org.aston.registrationservice.repository;

import org.aston.registrationservice.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    List<Pizza> findByUserId(Long userId);
   List<Pizza>  findByUserIdAndPizzaName(Long userId, String pizzaName);
}

package org.aston.registrationservice.dto;

import org.aston.registrationservice.entity.User;




public record PizzaDto(Long id,
                       String pizzaName,
                       Integer count,
                       User user) {
}

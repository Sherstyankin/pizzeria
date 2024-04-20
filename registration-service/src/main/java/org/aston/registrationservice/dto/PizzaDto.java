package org.aston.registrationservice.dto;

import org.aston.registrationservice.entity.User;

import java.util.List;


public record PizzaDto(Long id,
                       String pizza_name,
                       Integer count,
                       User user) {
}

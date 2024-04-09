package org.aston.registrationservice.dto;

import org.aston.registrationservice.entity.User;

public record DeliveredOrdersDto(Long id,
                                 String pizza_name,
                                 Integer count,
                                 User user) {
}

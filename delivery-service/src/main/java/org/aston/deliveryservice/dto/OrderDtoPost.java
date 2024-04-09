package org.aston.deliveryservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderDtoPost {
    private long userId;
}

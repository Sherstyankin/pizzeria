package org.aston.deliveryservice.dto.Order;

import lombok.Builder;
import org.aston.deliveryservice.constants.CourierStatus;

@Builder
public record OrderStatusDto(CourierStatus status) {
}

package org.aston.deliveryservice.dto.Order;

import org.aston.deliveryservice.constants.CourierStatus;
import lombok.Builder;

@Builder
public record OrderStatusDto(CourierStatus status) {}

package org.aston.registrationservice.mapper;

import org.aston.registrationservice.dto.DeliveredOrdersDto;
import org.aston.registrationservice.dto.UserDto;
import org.aston.registrationservice.entity.DeliveredOrders;
import org.aston.registrationservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface DeliveredOrdersMapper {

    DeliveredOrdersMapper INSTANCE = Mappers.getMapper(DeliveredOrdersMapper.class);
    DeliveredOrders toDeliveredOrders(DeliveredOrdersDto deliveredOrdersDto);
    DeliveredOrdersDto toDeliveredOrdersDto(DeliveredOrders deliveredOrders);

}

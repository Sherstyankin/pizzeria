package org.aston.orderservice.mapper;

import org.aston.orderservice.dto.RequestOrderDto;
import org.aston.orderservice.dto.ResponseOrderDto;
import org.aston.orderservice.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order mapToEntity(RequestOrderDto dto);

    ResponseOrderDto mapToDto(Order entity);
}

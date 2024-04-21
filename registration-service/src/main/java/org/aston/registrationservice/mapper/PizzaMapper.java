package org.aston.registrationservice.mapper;

import org.aston.registrationservice.dto.PizzaDto;
import org.aston.registrationservice.entity.Pizza;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface PizzaMapper {

    PizzaMapper INSTANCE = Mappers.getMapper(PizzaMapper.class);

    Pizza toPizza(PizzaDto pizzaDto);

    PizzaDto toPizzaDto(Pizza pizza);

}

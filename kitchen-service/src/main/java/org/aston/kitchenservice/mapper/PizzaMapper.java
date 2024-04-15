package org.aston.kitchenservice.mapper;

import org.aston.kitchenservice.dto.PizzaDto;
import org.aston.kitchenservice.entity.Pizza;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PizzaMapper {
    PizzaMapper INSTANCE = Mappers.getMapper(PizzaMapper.class);
    PizzaDto toPizzaDto(Pizza pizza);
    Pizza toPizza(PizzaDto pizzaDto);
}

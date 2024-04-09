package org.aston.registrationservice.mapper;

import org.aston.registrationservice.dto.UserDto;
import org.aston.registrationservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserDto userDto);
    UserDto toUserDto(User user);
}

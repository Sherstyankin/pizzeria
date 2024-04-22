package org.aston.registrationservice.service;

import org.aston.registrationservice.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto registerUser(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto);

    UserDto getUserById(Long id);

    void deleteUser(Long id);

    List<UserDto> getAllUsers();

    Optional<UserDto> findByUsername(String username);

}

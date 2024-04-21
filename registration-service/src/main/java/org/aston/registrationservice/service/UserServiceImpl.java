package org.aston.registrationservice.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aston.registrationservice.dto.UserDto;
import org.aston.registrationservice.entity.Role;
import org.aston.registrationservice.entity.User;
import org.aston.registrationservice.exceptions.ObjectNotFoundException;
import org.aston.registrationservice.mapper.UserMapper;
import org.aston.registrationservice.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDto registerUser(@Valid UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, @Valid UserDto userDto) {
        validateUserById(id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        if (userDto != null) {
            user.setUsername(userDto.username());
            user.setPassword(userDto.password());
            user.setEmail(userDto.email());
            user.setAddress(userDto.address());
            user.setRole(userDto.role());
        }
        user = userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        validateUserById(id);
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toUserDto).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        validateUserById(id);
        userRepository.deleteById(id);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.map(UserMapper.INSTANCE::toUserDto);
    }

    private void validateUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ObjectNotFoundException("User with id = " + id + " doesn't exist.");
        }
    }

}

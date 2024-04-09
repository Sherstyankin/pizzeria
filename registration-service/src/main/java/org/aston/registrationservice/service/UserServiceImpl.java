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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
@Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto registerUser(@Valid UserDto userDto) {
        User user = userMapper.toUser(userDto);
        user.setRole(Role.USER);
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

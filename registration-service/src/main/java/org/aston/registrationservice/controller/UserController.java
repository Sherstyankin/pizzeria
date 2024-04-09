package org.aston.registrationservice.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.aston.registrationservice.dto.UserDto;
import org.aston.registrationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

public class UserController {
    private final UserService userService;
@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/api/users/{id}")
    public ResponseEntity<UserDto> registerNewUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok().body(userService.registerUser(userDto));
    }
    @GetMapping(value = "/api/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/allUsers")
    public ResponseEntity<List<UserDto>> findAllUsers(UserDto userDto){
        List<UserDto> userDtos = userService.getAllUsers();
        if (userDtos.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        try {
            UserDto updatedUser = userService.updateUser(id, userDto);
            return ResponseEntity.ok(updatedUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

@GetMapping("/api/users/username/{username}")
public ResponseEntity<UserDto> findByUsername(@PathVariable String username) {
    Optional<UserDto> userDtoOptional = userService.findByUsername(username);
    return userDtoOptional.map(userDto -> ResponseEntity.ok().body(userDto))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
}
}

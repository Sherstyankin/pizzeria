package org.aston.registrationservice.security;

import org.aston.registrationservice.dto.UserDto;
import org.aston.registrationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"unused"})
@Service
public class AuthenticationService implements UserDetailsService {
    private final UserRepository userRepository;
        @Autowired
        public AuthenticationService( UserRepository userRepository) {
            this.userRepository = userRepository;
        }
// метод для проверки подлинности залогининого пользователя
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return userRepository.findByUsername(username)
                    .map(user -> new org.springframework.security.core.userdetails.User(
                            user.getUsername(),
                            user.getPassword(),
                            Collections.singleton(user.getRole())
                    ))
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));


        }

    }

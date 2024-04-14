package org.aston.registrationservice.service;

import lombok.RequiredArgsConstructor;
import org.aston.registrationservice.dto.JwtRequest;
import org.aston.registrationservice.dto.JwtResponse;
import org.aston.registrationservice.exceptions.AppError;
import org.aston.registrationservice.security.UserDetailsServiceImpl;
import org.aston.registrationservice.security.jwt.JwtTokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtTokenUtils jwtTokenUtils;

    private final AuthenticationManager authenticationManager;


    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.username(),
                    authRequest.password()));

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(),
                    "Not correct login or password"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authRequest.username());
        String token = jwtTokenUtils.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
    }


}

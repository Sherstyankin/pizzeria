package org.aston.orderservice.client;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aston.orderservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@AllArgsConstructor
public class RegistrationClient {

    @Value("${registration.url}")
    private String REGISTRATION_URL;
    private final WebClient webClient = WebClient.builder().build();

    public UserDto checkUser(Long userId) {
        return Objects.requireNonNull(webClient.get()
                .uri(REGISTRATION_URL + "/{userId}", userId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(UserDto.class)
                .block()).getBody();
    }
}

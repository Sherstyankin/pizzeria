package org.aston.orderservice.client;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@AllArgsConstructor
public class RegistrationClient {

    @Value("${registration.url}")
    private String REGISTRATION_URL;
    private final WebClient webClient = WebClient.builder().build();

    public Long checkUser(Long userId) {

        return webClient.get()
                .uri(REGISTRATION_URL + "/users/" + userId)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Long.class)
                .block();
    }
}

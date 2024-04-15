package org.aston.orderservice.client;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aston.orderservice.dto.KitchenDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@AllArgsConstructor
public class KitchenClient {

    @Value("${kitchen.url}")
    private String KITCHEN_URL;
    private final WebClient webClient = WebClient.builder().build();

    public void sendKitchenDto(KitchenDto dto) {

        webClient.post()
                .uri(KITCHEN_URL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(dto)
                .retrieve()
                .toEntity(Object.class)
                .block();
    }
}

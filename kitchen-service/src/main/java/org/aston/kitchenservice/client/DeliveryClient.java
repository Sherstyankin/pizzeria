package org.aston.kitchenservice.client;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.aston.kitchenservice.dto.DeliveryDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
@AllArgsConstructor
public class DeliveryClient {

    @Value("${delivery.url}")
    private String DELIVERY_URL;
    private WebClient webClient = WebClient.builder().build();

    public void sendDeliveryDto(DeliveryDto deliveryDto) {
        webClient.post()
                .uri(DELIVERY_URL)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(deliveryDto)
                .retrieve()
                .toEntity(String.class)
                .block();
    }
}

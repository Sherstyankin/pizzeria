package org.aston.deliveryservice.client;

import org.aston.deliveryservice.constants.client.UserClientPath;
import org.aston.deliveryservice.dto.Pizza.PizzaDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class UserServiceClient {

    private WebClient client;

    public UserServiceClient() {
        client = WebClient.create();
    }

    public void sendPost(List<PizzaDto> pizzaDtoList) {
        client.post()
                .uri(UserClientPath.POST)
                .bodyValue(pizzaDtoList)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

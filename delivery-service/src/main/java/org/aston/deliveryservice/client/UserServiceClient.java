package org.aston.deliveryservice.client;

import org.aston.deliveryservice.constants.client.UserClientPath;
import org.aston.deliveryservice.dto.User.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserServiceClient {
    
    private WebClient client;

    public UserServiceClient() {
        client = WebClient.create();
    }

    public void sendPost(UserDto dto) {
        client.post()
        .uri(UserClientPath.POST)
        .bodyValue(dto)
        .retrieve()
        .bodyToMono(String.class)
        .block();
    }
}

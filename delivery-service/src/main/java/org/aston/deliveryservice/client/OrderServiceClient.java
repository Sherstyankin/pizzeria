package org.aston.deliveryservice.client;

import static org.aston.deliveryservice.constants.client.OrderClientPath.GET;
import static org.aston.deliveryservice.constants.client.OrderClientPath.PATCH;
import org.aston.deliveryservice.dto.Order.OrderIdDto;
import org.aston.deliveryservice.dto.Order.UserIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderServiceClient {
    @Autowired
    private ObjectMapper mapper;
    private WebClient client = WebClient.create();

    public UserIdDto sendGet(OrderIdDto dto) {
        var userId = client.get()
        .uri(GET + "/" + dto.orderId())
        .retrieve()
        .bodyToMono(String.class)
        .block();

        try {
            return mapper.readValue(userId, UserIdDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void patchStatus(OrderIdDto dto) {
        client.patch()
        .uri(PATCH + "/" + dto.orderId())
        .retrieve()
        .bodyToMono(String.class)
        .block();
    }
}

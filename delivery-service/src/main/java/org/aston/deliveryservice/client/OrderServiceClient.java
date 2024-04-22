package org.aston.deliveryservice.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.aston.deliveryservice.dto.Order.OrderIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import static org.aston.deliveryservice.constants.client.OrderClientPath.GET;
import static org.aston.deliveryservice.constants.client.OrderClientPath.PATCH;

@Component
@RequiredArgsConstructor
public class OrderServiceClient {
    @Autowired
    private ObjectMapper mapper;
    private WebClient client = WebClient.create();

    public Long sendGet(Long orderId) {
        return client.get()
                .uri(GET + "/" + orderId)
                .retrieve()
                .bodyToMono(Long.class)
                .block();

        //try {
        //    return mapper.readValue(userId, UserIdDto.class);
        //} catch (JsonProcessingException e) {
        //    e.printStackTrace();
        //}
        //return null;
    }

    public void patchStatus(OrderIdDto dto) {
        client.patch()
                .uri(PATCH + "/" + dto.orderId())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}

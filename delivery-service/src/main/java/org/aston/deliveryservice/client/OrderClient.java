package org.aston.deliveryservice.client;

import static org.aston.deliveryservice.constants.ClientPath.ORDER_GET;
import org.aston.deliveryservice.dto.OrderDtoPost;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class OrderClient {
    private WebClient client;

    public OrderClient() {
        client = WebClient.create(ORDER_GET);
    }

    public Mono<OrderDtoPost> sendGet(long orderId) {
        var result = client.get().header("orderId", Long.toString(orderId)).retrieve();
        return result.bodyToMono(OrderDtoPost.class);
    }
}

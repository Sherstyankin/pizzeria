package org.aston.orderservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aston.orderservice.dto.RequestOrderDto;
import org.aston.orderservice.dto.ResponseOrderDto;
import org.aston.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseOrderDto findOrderById(@RequestParam(name = "id") Long orderId) {
        return orderService.findOrderById(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postOrder(@RequestBody @Valid RequestOrderDto dto) {
        orderService.postOrder(dto);
    }

    @GetMapping("/{id}")
    public Long findUserIdByOrderId(@PathVariable(name = "id") Long orderId) {
        return orderService.findUserIdByOrderId(orderId);
    }

    @PatchMapping("/{id}")
    public void changeOrderStatus(@PathVariable(name = "id") Long orderId) {
        orderService.changeOrderStatus(orderId);
    }

}

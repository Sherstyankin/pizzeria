package org.aston.registrationservice.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.aston.registrationservice.dto.DeliveredOrdersDto;
import org.aston.registrationservice.service.DeliveredOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DeliveredOrdersController {
    private final DeliveredOrdersService deliveredOrdersService;
@Autowired
    public DeliveredOrdersController(DeliveredOrdersService deliveredOrdersService) {
        this.deliveredOrdersService = deliveredOrdersService;
    }
    @PostMapping(value = "/api/orders/{id}")
    public ResponseEntity<DeliveredOrdersDto> saveNewDeliveredOrder(@RequestBody DeliveredOrdersDto deliveredOrdersDto){
        return ResponseEntity.ok().body(deliveredOrdersService.saveNewDeliveredOrder(deliveredOrdersDto));
    }
    @GetMapping(value = "/api/orders/{id}")
    public ResponseEntity<DeliveredOrdersDto> getDeliveredOrderById(@PathVariable Long id) {
        DeliveredOrdersDto deliveredOrdersDto = deliveredOrdersService.getDeliveredOrderById(id);
        if (deliveredOrdersDto != null) {
            return new ResponseEntity<>(deliveredOrdersDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/allOrders")
    public ResponseEntity<List<DeliveredOrdersDto>> findAllOrders(DeliveredOrdersDto deliveredOrdersDto){
        List<DeliveredOrdersDto> ordersDto = deliveredOrdersService.getAllDeliveredOrders();
        if (ordersDto.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ordersDto, HttpStatus.OK);
    }

    @DeleteMapping("/api/orders/{id}")
    public ResponseEntity<Void> deleteDeliveredOrder(@PathVariable Long id) {
        try {
            deliveredOrdersService.deleteDeliveredOrder(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/orders/{id}")
    public ResponseEntity<DeliveredOrdersDto> updateDeliveredOrder(@PathVariable Long id,
                                                                   @Valid @RequestBody DeliveredOrdersDto deliveredOrdersDto) {
        try {
            DeliveredOrdersDto updatedOrder = deliveredOrdersService.updateDeliveredOrder(id, deliveredOrdersDto);
            return ResponseEntity.ok(updatedOrder);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/api/orders//{userId}/orders/{orderId}")
//    public ResponseEntity<DeliveredOrdersDto> findDeliveredOrderByUserId(
//            @PathVariable Long userId,
//            @PathVariable Long orderId) {
//        try {
//            DeliveredOrdersDto deliveredOrdersDto = deliveredOrdersService.findDeliveredOrderByUserId(userId, orderId);
//            return ResponseEntity.ok(deliveredOrdersDto);
//        } catch (IllegalArgumentException | EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//    }
//@GetMapping("/api/orders/{userId}")
//public ResponseEntity<List<DeliveredOrdersDto>> findAllDeliveredOrdersByUserId(@PathVariable Long userId) {
//    List<DeliveredOrdersDto> deliveredOrdersDtos = deliveredOrdersService.findAllDeliveredOrdersByUserId(userId)
//            .stream()
//            .map(deliveredOrders -> new DeliveredOrdersDto(
//                    deliveredOrders.id(),
//                    deliveredOrders.pizza_name(),
//                    deliveredOrders.count(),
//                    deliveredOrders.user())
//            )
//            .collect(Collectors.toList());
//
//    return ResponseEntity.ok(deliveredOrdersDtos);
//}
@GetMapping("/api/orders/{userId}")
public ResponseEntity<List<DeliveredOrdersDto>> findAllDeliveredOrdersByUserId(@PathVariable Long userId) {
    List<DeliveredOrdersDto> deliveredOrdersDtos = deliveredOrdersService.findAllDeliveredOrdersByUserId(userId);
    return ResponseEntity.ok(deliveredOrdersDtos);
}
}

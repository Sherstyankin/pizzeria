package org.aston.registrationservice.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aston.registrationservice.dto.PizzaDto;
import org.aston.registrationservice.dto.ResponsePizzaDto;
import org.aston.registrationservice.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class PizzaController {
    private final PizzaService pizzaService;


    @PostMapping("/api/pizzas")
    public ResponseEntity<String> saveNewPizza(@RequestBody List<PizzaDto> pizzaDtos) {
        try {
            pizzaService.saveNewPizza(pizzaDtos);
            String responseMessage = "Pizzas saved successfully";
            return ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving pizzas: " + e.getMessage());
        }
    }

    @GetMapping(value = "/api/pizzas/get/{id}")
    public ResponseEntity<PizzaDto> getPizzaById(@PathVariable Long id) {
        PizzaDto pizzaDto = pizzaService.getPizzaById(id);
        if (pizzaDto != null) {
            return new ResponseEntity<>(pizzaDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/api/allPizzas")
    public ResponseEntity<List<PizzaDto>> findAllPizzas() {
        List<PizzaDto> pizzasDto = pizzaService.getAllPizzas();
        if (pizzasDto.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(pizzasDto, HttpStatus.OK);
    }


    @PutMapping("/api/pizzas/{id}")
    public ResponseEntity<List<PizzaDto>> updatePizza(@PathVariable Long id,
                                                      @Valid @RequestBody List<PizzaDto> pizzaDto) {
        try {
            List<PizzaDto> updatedPizza = pizzaService.updatePizza(id, pizzaDto);
            return ResponseEntity.ok(updatedPizza);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/pizzas/users/{userId}")
    public ResponseEntity<List<ResponsePizzaDto>> getPizzasByUserId(@PathVariable Long userId) {
        List<ResponsePizzaDto> pizzas = pizzaService.getPizzasByUserId(userId);
        if (pizzas.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        } else {
            return ResponseEntity.ok(pizzas);
        }
    }

//    @GetMapping("/api/pizzas/find/pizzaname")
//    public ResponseEntity<?> findPizzaByUserAndName(@RequestParam User user, @RequestParam String pizzaName) {
//        Optional<Pizza> pizzaOpt = pizzaService.findByUser_IdAndPizzaName(user, pizzaName);
//        return pizzaOpt.map(pizza -> ResponseEntity.ok().body(pizza))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}

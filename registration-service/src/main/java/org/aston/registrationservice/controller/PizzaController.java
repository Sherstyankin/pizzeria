package org.aston.registrationservice.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aston.registrationservice.dto.PizzaDto;
import org.aston.registrationservice.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class PizzaController {
    private final PizzaService pizzaService;


    @PostMapping("/api/pizzas/{userId}")
    public ResponseEntity<String> saveNewPizza(@PathVariable Long userId, @RequestBody List<PizzaDto> pizzaDtos) {
        try {
            pizzaService.saveNewPizza(userId, pizzaDtos);
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


}

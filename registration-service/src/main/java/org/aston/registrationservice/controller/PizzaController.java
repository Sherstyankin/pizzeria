package org.aston.registrationservice.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.aston.registrationservice.dto.PizzaDto;
import org.aston.registrationservice.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaController {
    private final PizzaService pizzaService;
@Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }
    @PostMapping(value = "/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> saveNewPizza(@RequestBody PizzaDto pizzaDto){
        return ResponseEntity.ok().body(pizzaService.saveNewPizza(pizzaDto));
    }
    @GetMapping(value = "/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> getPizzaById(@PathVariable Long id) {
        PizzaDto pizzaDto = pizzaService.getPizzaById(id);
        if (pizzaDto != null) {
            return new ResponseEntity<>(pizzaDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/allPizzas")
    public ResponseEntity<List<PizzaDto>> findAllPizzas(PizzaDto pizzaDto){
        List<PizzaDto> pizzasDto = pizzaService.getAllPizzas();
        if (pizzasDto.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(pizzasDto, HttpStatus.OK);
    }

    @DeleteMapping("/api/pizzas/{id}")
    public ResponseEntity<Void> deletePizza(@PathVariable Long id) {
        try {
            pizzaService.deletePizza(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> updatePizza(@PathVariable Long id,
                                                         @Valid @RequestBody PizzaDto pizzaDto) {
        try {
            PizzaDto updatedPizza = pizzaService.updatePizza(id, pizzaDto);
            return ResponseEntity.ok(updatedPizza);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


@GetMapping("/api/orders/{userId}")
public ResponseEntity<List<PizzaDto>> findAllPizzaByUserId(@PathVariable Long userId) {
    List<PizzaDto> pizzaDtos = pizzaService.findAllPizzaByUserId(userId);
    return ResponseEntity.ok(pizzaDtos);
}
}

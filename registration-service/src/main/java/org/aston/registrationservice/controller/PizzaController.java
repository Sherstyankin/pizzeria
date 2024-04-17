package org.aston.registrationservice.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aston.registrationservice.dto.PizzaDto;
import org.aston.registrationservice.dto.UserDto;
import org.aston.registrationservice.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PizzaController {
    private final PizzaService pizzaService;
    @PostMapping(value = "/api/pizzas/{id}")
    public ResponseEntity<PizzaDto> saveNewPizza(@RequestBody PizzaDto pizzaDto){
        return ResponseEntity.ok().body(pizzaService.saveNewPizza(pizzaDto));
    }
    @GetMapping(value = "/api/pizzas/get/{id}")
    public ResponseEntity<PizzaDto> getPizzaById(@PathVariable Long id) {
        PizzaDto pizzaDto = pizzaService.getPizzaById(id);
//        return pizzaDto.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        if (pizzaDto != null) {
            return new ResponseEntity<>(pizzaDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/api/allPizzas")
    public ResponseEntity<List<PizzaDto>> findAllPizzas(){
        List<PizzaDto> pizzasDto = pizzaService.getAllPizzas();
        if (pizzasDto.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      //  return new ResponseEntity<>(pizzasDto, HttpStatus.OK);
        return ResponseEntity.ok(pizzasDto);
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


@GetMapping("/api/pizzas/users/{userId}")
public ResponseEntity<List<PizzaDto>> findAllByUser_Id(@PathVariable Long userId) {
    List<PizzaDto> pizzaDtos = pizzaService.findAllByUser_Id(userId);
    if (pizzaDtos.isEmpty()) {
        return ResponseEntity.ok(Collections.emptyList());
    } else {
        return ResponseEntity.ok(pizzaDtos);
    }
    // return ResponseEntity.ok(pizzaDtos);
}
}

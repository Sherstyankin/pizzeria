package org.aston.kitchenservice.exception.pizza;

public class PizzaNotFoundException extends RuntimeException {
    public PizzaNotFoundException(long id) {
        super(String.format("Пицца с id %s не найдена", id));
    }
}

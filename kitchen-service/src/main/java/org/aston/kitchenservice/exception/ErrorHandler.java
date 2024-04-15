package org.aston.kitchenservice.exception;

import org.aston.kitchenservice.exception.pizza.PizzaNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler({PizzaNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlePizzaNotFoundException(final PizzaNotFoundException e) {
        return new ErrorResponse("Пицца не найдена", e.getMessage());
    }
}

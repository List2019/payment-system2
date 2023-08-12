package com.payment_system.controller;

import com.payment_system.model.exception.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = AccountNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public void accountNotFoundException() {}
}

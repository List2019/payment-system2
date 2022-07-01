package com.demo.controller;

import com.demo.exception.AccountNotFoundException;
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

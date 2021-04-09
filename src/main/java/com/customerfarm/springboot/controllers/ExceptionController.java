package com.customerfarm.springboot.controllers;

import com.customerfarm.springboot.exceptions.AccountNotFoundException;
import com.customerfarm.springboot.exceptions.CustomerNotFoundException;
import com.customerfarm.springboot.exceptions.ErrorResponse;
import com.customerfarm.springboot.exceptions.FarmNotFoundException;
import com.customerfarm.springboot.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({AccountNotFoundException.class, CustomerNotFoundException.class, FarmNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception exception){

        ErrorResponse error = new ErrorResponse("NOT_FOUND_ERROR", exception.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}

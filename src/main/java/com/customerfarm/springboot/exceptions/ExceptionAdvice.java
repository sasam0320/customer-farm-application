package com.customerfarm.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler({AccountNotFoundException.class, CustomerNotFoundException.class, FarmNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(Exception exception){

        ErrorResponse error = new ErrorResponse("NOT_FOUND_ERROR", exception.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}

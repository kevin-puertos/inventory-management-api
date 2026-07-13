package com.kevinpuertos.inventory_management_api.exceptions.controller;

import com.kevinpuertos.inventory_management_api.exceptions.ResourceAlreadyExistException;
import com.kevinpuertos.inventory_management_api.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //ResourceAlreadyExist
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<String> handleResourceAlreadyExist(ResourceAlreadyExistException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public  ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }



}

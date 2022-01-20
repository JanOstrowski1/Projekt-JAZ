package com.example.demo.exceptions.exceptionsHandler;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.QuoteAlreadyExistException;
import com.example.demo.exceptions.QuoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(QuoteNotFoundException.class)
    public ResponseEntity handleNotFound(){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(QuoteAlreadyExistException.class)
    public ResponseEntity handleAlreadyExist(){
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity handleInvalidData(){
        return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }
}

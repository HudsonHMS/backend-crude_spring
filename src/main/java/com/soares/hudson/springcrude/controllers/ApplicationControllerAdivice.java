package com.soares.hudson.springcrude.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.soares.hudson.springcrude.exceptions.RegistroNaoEncontradoException;
import com.soares.hudson.springcrude.models.ResponseObject;

@RestControllerAdvice
public class ApplicationControllerAdivice {

    @ExceptionHandler( RegistroNaoEncontradoException.class )
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseObject<Object> handleRegistroNaoEncontrado( RegistroNaoEncontradoException ex ) {
        return new ResponseObject<>(null, ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

}

package com.soares.hudson.springcrude.controllers;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.soares.hudson.springcrude.exceptions.RegistroNaoEncontradoException;
import com.soares.hudson.springcrude.models.ResponseObject;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestControllerAdvice
@AllArgsConstructor
public class ApplicationControllerAdivice {

    private MessageSource messageSource;

    @ExceptionHandler( RegistroNaoEncontradoException.class )
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ResponseObject<Object> handleRegistroNaoEncontrado( RegistroNaoEncontradoException ex ) {
        return new ResponseObject<>(null, ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler( EntityNotFoundException.class )
    @ResponseStatus( code = HttpStatus.NOT_FOUND )
    public ResponseObject<Object> handleEntityNotFounf( EntityNotFoundException ex, HttpServletRequest request ) {
        final String[] path = request.getRequestURI().split("/");
        String id = path[path.length-1];

        String msg = messageSource.getMessage("aula.nao.encontrada", new Object[]{id}, null, Locale.getDefault());
        return new ResponseObject<>(null, msg, HttpStatus.NOT_FOUND.value());
    }

}

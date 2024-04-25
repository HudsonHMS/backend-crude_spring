package com.soares.hudson.springcrude.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.soares.hudson.springcrude.exceptions.RegistroNaoEncontradoException;
import com.soares.hudson.springcrude.models.ResponseObject;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
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

    @ExceptionHandler( ConstraintViolationException.class )
    public ResponseObject<Object> handleLengthValidations( ConstraintViolationException ex, HttpServletRequest request ) {
        List<String> msgs =  new ArrayList<>();
        ConstraintViolation<?> cons = null;
        Iterator<ConstraintViolation<?>> iterator = ex.getConstraintViolations().iterator();
        if( iterator.hasNext() ) {
            cons = iterator.next(); 
        }

        if( cons != null ) {
            msgs.add(cons.getPropertyPath().toString());
            msgs.add(cons.getMessage());
        }
        

        String msg = messageSource.getMessage("validacao.validacao", msgs.toArray(), "Campo obrigatório", Locale.getDefault());
        return new ResponseObject<>(null, msg, HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseObject<Object> handleArgumentsValidations( MethodArgumentNotValidException ex, HttpServletRequest request ) {
        String fieldName = ex.getBindingResult().getFieldError() != null ? ex.getBindingResult().getFieldError().getField() : "";
        String msg = messageSource.getMessage("validacao.invalido", new Object[]{fieldName}, "Campo inválido.", Locale.getDefault());
        return new ResponseObject<>(null, msg, HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler( MethodArgumentTypeMismatchException.class )
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseObject<Object> handleArgumentsMismatchValidations( MethodArgumentTypeMismatchException ex, HttpServletRequest request ) {
        String msg = messageSource.getMessage("validacao.conversao", new Object[]{ex.getPropertyName()}, "Campo inválido.", Locale.getDefault());
        return new ResponseObject<>(null, msg, HttpStatus.BAD_REQUEST.value());
    }

}

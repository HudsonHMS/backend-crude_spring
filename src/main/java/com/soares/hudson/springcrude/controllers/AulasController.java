package com.soares.hudson.springcrude.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soares.hudson.springcrude.models.ResponseObject;
import com.soares.hudson.springcrude.services.AulasService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/aulas")
@AllArgsConstructor
public class AulasController {

    private AulasService service;

    @GetMapping("hello")
    public String hello() {
        return "Hello";
    }

    @DeleteMapping("/{id}")
    public ResponseObject<Boolean> deletaAula( @NotNull @PathVariable Long id, HttpServletResponse response ) {
        response.setStatus(204);
        service.deletarAula(id);
        return new ResponseObject<>(true, "Aula deletada com sucesso", HttpStatus.NO_CONTENT.value());
    }

}

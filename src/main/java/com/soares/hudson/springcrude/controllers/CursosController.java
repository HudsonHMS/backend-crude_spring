package com.soares.hudson.springcrude.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.soares.hudson.springcrude.models.Cursos;
import com.soares.hudson.springcrude.models.ResponseObject;
import com.soares.hudson.springcrude.repository.CursosRepository;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cursos")
@AllArgsConstructor
public class CursosController {

    private final CursosRepository repository;

    @GetMapping("")
    public List<Cursos> getCursos() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public ResponseObject<Cursos> postMethodName(@RequestBody Cursos entity) {
        try {
            Cursos curso = this.repository.save(entity);
            return new ResponseObject<Cursos>(curso, "Curso cadastrado com sucesso!", HttpStatus.CREATED.value());
        } catch (Exception e) {
            return new ResponseObject<Cursos>(null, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }

    }

}

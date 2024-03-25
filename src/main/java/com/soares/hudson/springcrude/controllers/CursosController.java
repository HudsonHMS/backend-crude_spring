package com.soares.hudson.springcrude.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.soares.hudson.springcrude.models.Cursos;
import com.soares.hudson.springcrude.repository.CursosRepository;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/cursos")
@AllArgsConstructor
public class CursosController {

    private final CursosRepository repository;

    @GetMapping("")
    public List<Cursos> getCursos() {
        return this.repository.findAll();
    }

}

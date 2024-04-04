package com.soares.hudson.springcrude.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.soares.hudson.springcrude.models.Cursos;
import com.soares.hudson.springcrude.models.ResponseObject;
import com.soares.hudson.springcrude.repository.CursosRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("visualizar/{id}")
    @Operation(summary = "Visualiza um curso pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(description = "Found", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Cursos.class))
            })
    })
    public ResponseObject<Cursos> view(@PathVariable Long id) {
        try {
            final Cursos curso = this.repository.getCursoPorId(id);
            return new ResponseObject<Cursos>(curso, "Ok", HttpStatus.OK.value());
        } catch (Exception e) {
            return new ResponseObject<Cursos>(null, e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @PutMapping("atualizar")
    @Operation(summary = "Atualiza um curso pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(description = "Curso atualizado", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Cursos.class))
            })
    })
    public ResponseObject<Cursos> atualizar(@RequestBody Cursos curso) {
        try {
            return new ResponseObject<Cursos>(this.repository.save(curso), "Curso atualizado", HttpStatus.OK.value());
        } catch (Exception e) {
            return new ResponseObject<Cursos>(null, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

    @DeleteMapping("deletar/{id}")
    @Operation(summary = "Deleta um curso pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(description = "Curso deletado", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Cursos.class))
            })
    })
    public ResponseObject<Boolean> deletar(@PathVariable Long id) {
        try {
            this.repository.deleteById(id);
            return new ResponseObject<Boolean>(true, "Curso atualizado", HttpStatus.OK.value());
        } catch (Exception e) {
            return new ResponseObject<Boolean>(false, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }
    }

}

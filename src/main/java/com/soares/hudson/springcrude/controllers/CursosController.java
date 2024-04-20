package com.soares.hudson.springcrude.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.soares.hudson.dto.CursoDTO;
import com.soares.hudson.springcrude.models.Cursos;
import com.soares.hudson.springcrude.models.ResponseObject;
import com.soares.hudson.springcrude.services.CursosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@RestController
@RequestMapping("/cursos")
@AllArgsConstructor
public class CursosController {

    private final CursosService cursoService;

    @GetMapping("")
    public ResponseObject<List<CursoDTO>> getCursos() {
        return new ResponseObject<>(cursoService.getAllActiveCursos(), "OK", HttpStatus.OK.value());
    }

    @PostMapping("")
    public ResponseObject<CursoDTO> cadastrarCurco(@RequestBody @Valid @NotNull CursoDTO entity) {
        try {
            CursoDTO curso = cursoService.save(entity);
            return new ResponseObject<>(curso, "Curso cadastrado com sucesso!", HttpStatus.CREATED.value());
        } catch (Exception e) {
            return new ResponseObject<>(null, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        }

    }

    @GetMapping("visualizar/{id}")
    @Operation(summary = "Visualiza um curso pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(description = "Found", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Cursos.class))
            })
    })
    public ResponseObject<CursoDTO> view(@PathVariable @NotNull @Positive Long id) {
        final CursoDTO curso = cursoService.getCursoById( id );
        return new ResponseObject<>(curso, "OK", HttpStatus.OK.value());
    }

    @PutMapping("atualizar")
    @Operation(summary = "Atualiza um curso pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(description = "Curso atualizado", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Cursos.class))
            })
    })
    public ResponseObject<CursoDTO> atualizar(@RequestBody @Valid CursoDTO curso) {
       return new ResponseObject<>(cursoService.edit(curso), "Curso atualizado", HttpStatus.OK.value()); 
    }

    @DeleteMapping("deletar/{id}")
    @Operation(summary = "Deleta um curso pelo seu id")
    @ApiResponses(value = {
            @ApiResponse(description = "Curso deletado", responseCode = "200", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Cursos.class))
            })
    })
    public ResponseObject<Boolean> deletar(@PathVariable @NotNull @Positive Long id) {
       
        cursoService.deleteById(id);
        return new ResponseObject<>(true, "Curso atualizado", HttpStatus.OK.value());
       
    }

}

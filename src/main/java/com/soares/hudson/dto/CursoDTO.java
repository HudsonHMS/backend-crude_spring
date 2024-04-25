package com.soares.hudson.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.soares.hudson.springcrude.enuns.CategoriaConverter;
import com.soares.hudson.springcrude.enuns.CategoriaEnum;
import com.soares.hudson.springcrude.enuns.StatusConverter;
import com.soares.hudson.springcrude.enuns.StatusEnum;
import com.soares.hudson.springcrude.models.Cursos;
import com.soares.hudson.utils.ValueOfEnum;

import jakarta.persistence.Convert;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Component
@Validated
public class CursoDTO {

    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 10, max = 255, message = "{nome.tamanho}")
    private String nome;

    @NotNull
    private Double valor;

    @NotNull
    @Convert(converter = CategoriaConverter.class)
    private CategoriaEnum categoria;

    @NotNull
    @Convert(converter = StatusConverter.class)
    private StatusEnum status;

    List<AulaDTO> aulas;

    @Autowired
    public CursoDTO() {
        if( null == aulas ) {
            aulas = new ArrayList<>();
        }
    }

    public CursoDTO(Cursos cursos) {
        if (null != cursos) {
            id = cursos.getId();
            nome = cursos.getNome();
            categoria = cursos.getCategoria();
            valor = cursos.getValor();
            status = cursos.getStatus();
            aulas = cursos.getAulas().stream().map(aula -> new AulaDTO(aula.getId(), aula.getNome(), aula.getUrl()))
                    .collect(Collectors.toList());
        }
    }

    public static Cursos toEntity(CursoDTO cursoDTO) {
        if (null != cursoDTO) {
            return Cursos.cursoBuilder(cursoDTO);
        }
        return null;
    }

}

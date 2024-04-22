package com.soares.hudson.springcrude.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.Length;

import com.soares.hudson.dto.CursoDTO;
import com.soares.hudson.springcrude.enuns.CategoriaConverter;
import com.soares.hudson.springcrude.enuns.CategoriaEnum;
import com.soares.hudson.springcrude.enuns.StatusConverter;
import com.soares.hudson.springcrude.enuns.StatusEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Transactional
public class Cursos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 10, max = 255, message = "{nome.tamanho}")
    @Column(length = 255, nullable = false)
    private String nome;

    @NotNull
    @Column()
    private Double valor;

    @NotNull
    @Column(length = 50, nullable = false)
    @Convert(converter = CategoriaConverter.class)
    private CategoriaEnum categoria;

    @NotNull
    @Column(length = 2, nullable = false)
    @Convert(converter = StatusConverter.class)
    private StatusEnum status = StatusEnum.ATIVO;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cursos")
    private List<Aulas> aulas;

    public static Cursos cursoBuilder(Long id, String nome, Double valor, CategoriaEnum categoria, StatusEnum status,
            @NotNull List<Aulas> aulas) {
        Cursos cursos = new Cursos();
        cursos.setId(id);
        cursos.setNome(nome);
        cursos.setCategoria(categoria);
        cursos.setValor(valor);
        cursos.setStatus(status);
        cursos.setAulas(aulas);
        return cursos;
    }

    public static Cursos cursoBuilder(@NotNull CursoDTO cursoDTO) {
        Cursos cursos = new Cursos();
        cursos.setId(cursoDTO.getId());
        cursos.setNome(cursoDTO.getNome());
        cursos.setCategoria(cursoDTO.getCategoria());
        cursos.setValor(cursoDTO.getValor());
        cursos.setStatus(cursoDTO.getStatus());
        if (cursoDTO.getAulas() != null) {
            cursos.setAulas(cursoDTO.getAulas().stream()
                    .map(aula -> new Aulas(aula.id(), aula.nome(), aula.url(), cursos)).toList());
        } else {
            cursos.setAulas(new ArrayList<>());
        }
        return cursos;
    }

}

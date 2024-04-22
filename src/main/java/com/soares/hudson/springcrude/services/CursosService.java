package com.soares.hudson.springcrude.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.soares.hudson.dto.CursoDTO;
import com.soares.hudson.springcrude.enuns.StatusEnum;
import com.soares.hudson.springcrude.exceptions.RegistroNaoEncontradoException;
import com.soares.hudson.springcrude.models.Cursos;
import com.soares.hudson.springcrude.repository.CursosRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Validated
public class CursosService {

    private final CursosRepository cursosRepository;
  
    public List<CursoDTO> getAllActiveCursos() {
        try {
            StatusEnum[] status = new StatusEnum[]{StatusEnum.ATIVO};
            return cursosRepository.findByStatusIn( status )
                        .stream()
                        .map(CursoDTO::new)
                        .collect(Collectors.toList());
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }

    public CursoDTO getCursoById(@NotNull @Positive Long id) throws RegistroNaoEncontradoException {
        CursoDTO curso = new CursoDTO(cursosRepository.getCursoPorId(id));
        if (null == curso.getId() ) {
            throw new RegistroNaoEncontradoException(id);
        }
        return curso;
    }

    public CursoDTO save(@Valid @NotNull CursoDTO entity) throws IllegalArgumentException {
        Cursos curso = CursoDTO.toEntity(entity);
        return new CursoDTO(cursosRepository.save( curso ));
    }

    public CursoDTO edit(@Valid @NotNull CursoDTO entity) throws RegistroNaoEncontradoException {
        Cursos curso = cursosRepository.getCursoPorId(entity.getId());
        if (null == curso.getId()) {
            throw new RegistroNaoEncontradoException(entity.getId(), entity);
        }
        return new CursoDTO(cursosRepository.save(CursoDTO.toEntity(entity)));
    }

    public void deleteById(@NotNull @Positive Long id) {
        cursosRepository.deleteById(id);
    }

}

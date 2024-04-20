package com.soares.hudson.dto;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoRecordDTO(Long id, 
                       @NotBlank @NonNull String nome, 
                       @NotNull Double valor, 
                       @NotBlank @NotNull String categoria, 
                       @NotNull Integer status
                    ) {}

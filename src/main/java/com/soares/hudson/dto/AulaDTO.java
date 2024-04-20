package com.soares.hudson.dto;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.constraints.NotNull;

public record AulaDTO( @Nullable Long id,  @NotNull String nome, 
                       @NotNull String url ) {
}

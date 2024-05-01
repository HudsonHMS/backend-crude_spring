package com.soares.hudson.dto;

import java.util.List;

public record PagedCursoDTO( List<CursoDTO> cursoDTO, int totalPages, int totalRegistros ) {}

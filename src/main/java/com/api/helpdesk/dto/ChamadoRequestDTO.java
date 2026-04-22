package com.api.helpdesk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ChamadoRequestDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String descricao,
        @NotNull
        Long funcionarioId) {
}

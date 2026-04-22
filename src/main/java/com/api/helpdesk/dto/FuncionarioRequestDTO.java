package com.api.helpdesk.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FuncionarioRequestDTO(
        @NotBlank
        String nome,
        @Email
        @NotBlank
        String email) {
}

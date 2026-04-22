package com.api.helpdesk.dto;

import com.api.helpdesk.model.Funcionario;

public record FuncionarioResponseDTO(Long id, String nome, String email) {
    public FuncionarioResponseDTO(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getEmail());
    }
}

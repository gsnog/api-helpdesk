package com.api.helpdesk.dto;

import com.api.helpdesk.model.Chamado;

public record ChamadoResponseDTO(Long id, String titulo, String descricao, String status, String nomeFuncionario) {
    public ChamadoResponseDTO(Chamado chamado){
        this(chamado.getId(), chamado.getTitulo(), chamado.getDescricao(), chamado.getStatus(), chamado.getFuncionario().getNome());
    }
}

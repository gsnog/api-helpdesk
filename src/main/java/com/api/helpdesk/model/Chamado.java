package com.api.helpdesk.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String status;
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;


    public Chamado(String titulo, String descricao, String status, Funcionario funcionario){
        this.titulo=titulo;
        this.descricao=descricao;
        this.status=status;
        this.funcionario = funcionario;
    }
}

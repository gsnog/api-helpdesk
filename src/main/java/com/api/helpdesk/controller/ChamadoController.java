package com.api.helpdesk.controller;

import com.api.helpdesk.dto.ChamadoRequestDTO;
import com.api.helpdesk.dto.ChamadoResponseDTO;
import com.api.helpdesk.service.ChamadoService;
import com.api.helpdesk.service.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chamados")
public class ChamadoController {
    private ChamadoService chamadoService;
    private FuncionarioService funcionarioService;

    public ChamadoController(ChamadoService chamadoService, FuncionarioService funcionarioService){
        this.chamadoService = chamadoService;
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<ChamadoResponseDTO>> listarChamados(){
        List<ChamadoResponseDTO> chamados = chamadoService.listarChamados();
        return ResponseEntity.status(HttpStatus.OK).body(chamados);
    }

    @PutMapping("/{id}/fechar")
    public ResponseEntity<ChamadoResponseDTO> fecharChamado(@PathVariable Long id){
        try {
            ChamadoResponseDTO chamadoAtualizado = chamadoService.fecharStatus(id);
            return ResponseEntity.status(HttpStatus.OK).body(chamadoAtualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<ChamadoResponseDTO> criarChamado(@RequestHeader("X-Funcionario-Id") Long funcionarioId, @RequestBody ChamadoRequestDTO dto){
        ChamadoResponseDTO chamado = chamadoService.criarChamado(dto, funcionarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(chamado);
    }
}

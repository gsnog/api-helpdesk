package com.api.helpdesk.controller;

import com.api.helpdesk.dto.FuncionarioRequestDTO;
import com.api.helpdesk.dto.FuncionarioResponseDTO;
import com.api.helpdesk.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService){
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> criarFuncionario(@Valid @RequestBody FuncionarioRequestDTO dto){
        FuncionarioResponseDTO funcionario = funcionarioService.salvarFuncionario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioResponseDTO>> listarTodosFuncionarios(){
            List<FuncionarioResponseDTO> todosFuncionarios = funcionarioService.listarTodosFuncionarios();
            return ResponseEntity.status(HttpStatus.OK).body(todosFuncionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> buscarPorId(@PathVariable Long id){
        try {
            FuncionarioResponseDTO funcionario = funcionarioService.buscarPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(funcionario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizarFuncionario(@PathVariable Long id, @Valid @RequestBody FuncionarioRequestDTO dto){
        try{
            FuncionarioResponseDTO funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body(funcionarioAtualizado);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id){
        try{
            funcionarioService.deletarFuncionario(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

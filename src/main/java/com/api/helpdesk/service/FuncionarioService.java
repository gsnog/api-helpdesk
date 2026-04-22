package com.api.helpdesk.service;

import com.api.helpdesk.dto.FuncionarioRequestDTO;
import com.api.helpdesk.dto.FuncionarioResponseDTO;
import com.api.helpdesk.model.Funcionario;
import com.api.helpdesk.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }

    private Funcionario buscarEntidade(Long id){
        return funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Funcionário não encontrado"));
    }

    public FuncionarioResponseDTO buscarPorId(Long id){
        return new FuncionarioResponseDTO(buscarEntidade(id));
    }

    public List<FuncionarioResponseDTO> listarTodosFuncionarios(){
        return funcionarioRepository.findAll().stream().map(FuncionarioResponseDTO::new).toList();
    }

    public FuncionarioResponseDTO salvarFuncionario(FuncionarioRequestDTO dto){
        Funcionario novoFuncionario = new Funcionario(dto.nome(), dto.email());
        funcionarioRepository.save(novoFuncionario);
        return new FuncionarioResponseDTO(novoFuncionario);
    }

    public FuncionarioResponseDTO atualizarFuncionario(Long id, FuncionarioRequestDTO dto){
        Funcionario funcionarioAtualizado = buscarEntidade(id);
        funcionarioAtualizado.setNome(dto.nome());
        funcionarioAtualizado.setEmail(dto.email());
        funcionarioRepository.save(funcionarioAtualizado);
        return new FuncionarioResponseDTO(funcionarioAtualizado);
    }

    public void deletarFuncionario(Long id){
        Funcionario funcionarioDeletado = buscarEntidade(id);
        funcionarioRepository.delete(funcionarioDeletado);
    }
}

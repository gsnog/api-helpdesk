package com.api.helpdesk.service;

import com.api.helpdesk.dto.ChamadoRequestDTO;
import com.api.helpdesk.dto.ChamadoResponseDTO;
import com.api.helpdesk.model.Chamado;
import com.api.helpdesk.model.Funcionario;
import com.api.helpdesk.repository.ChamadoRepository;
import com.api.helpdesk.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChamadoService {
    private ChamadoRepository chamadoRepository;
    private FuncionarioRepository funcionarioRepository;

    public ChamadoService(ChamadoRepository chamadoRepository, FuncionarioRepository funcionarioRepository){
        this.chamadoRepository = chamadoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    private Chamado buscarEntidade(Long id){
        return chamadoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Chamado não encontrado"));
    }

    public ChamadoResponseDTO buscarPorId(Long id){
        return new ChamadoResponseDTO(buscarEntidade(id));
    }

    public List<ChamadoResponseDTO> listarChamados(){
        return chamadoRepository.findAll().stream().map(ChamadoResponseDTO::new).toList();
    }

    public ChamadoResponseDTO criarChamado(ChamadoRequestDTO dto, Long funcionarioId){
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId).orElseThrow(() -> new IllegalArgumentException("Funcionario não encontrado"));
        Chamado chamado = new Chamado(dto.titulo(), dto.descricao(), "Aberto", funcionario);
        chamadoRepository.save(chamado);
        return new ChamadoResponseDTO(chamado);
    }

    public ChamadoResponseDTO fecharStatus(Long id){
        Chamado chamadoAtualizado = buscarEntidade(id);
        chamadoAtualizado.setStatus("Fechado");
        chamadoRepository.save(chamadoAtualizado);
        return new ChamadoResponseDTO(chamadoAtualizado);
    }
}

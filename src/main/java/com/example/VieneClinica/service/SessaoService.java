package com.example.VieneClinica.service;

import com.example.VieneClinica.dto.sessao.SessaoDTO;
import com.example.VieneClinica.dto.sessao.SessaoResponse;
import com.example.VieneClinica.entity.Paciente;
import com.example.VieneClinica.entity.Sessao;
import com.example.VieneClinica.entity.Usuario;
import com.example.VieneClinica.enums.StatusSessao;
import com.example.VieneClinica.mapper.SessaoMapper;
import com.example.VieneClinica.repository.PacienteRepository;
import com.example.VieneClinica.repository.SessaoRepository;
import com.example.VieneClinica.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final SessaoMapper sessaoMapper;
    private final UsuarioRepository usuarioRepository;
    private final PacienteRepository pacienteRepository;

    public SessaoResponse agendarSessao(Long profissionalId, Long pacienteId, SessaoDTO dto){

        Usuario usuario = usuarioRepository.findById(profissionalId)
                .orElseThrow(()-> new EntityNotFoundException("Profissional não encontrado"));

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(()-> new EntityNotFoundException("Paciente não encontrado"));

        if (sessaoRepository.existsByDataSessao(dto.dataSessao())){
            throw new DataIntegrityViolationException("Já existe uma consulta agendada para esse horário");
        }

        Sessao sessao = sessaoMapper.toEntity(dto);
        sessao.setDescricao(dto.descricao());
        sessao.setStatus(StatusSessao.AGENDADO);
        sessao.setPaciente(paciente);
        sessao.setUsuario(usuario);
        sessao.setDataSessao(dto.dataSessao());

        sessaoRepository.save(sessao);
        return sessaoMapper.toResponse(sessao);
    }

    public void alterarStatusSessao(Long idSessao, StatusSessao status){
        Sessao sessao = sessaoRepository.findById(idSessao)
                .orElseThrow(()-> new EntityNotFoundException("Consulta não encontrada"));

        if (sessao.getStatus() == StatusSessao.FALTA || sessao.getStatus() == StatusSessao.CONCLUIDO){
            throw new RuntimeException("Você não pode alterar os status dessa sessão");
        } else {
            sessao.setStatus(status);
        }
    }

    public void reagendarSessao(Long idSessao, OffsetDateTime novaDataSessao){
        Sessao sessao = sessaoRepository.findById(idSessao)
                .orElseThrow(()-> new EntityNotFoundException("Consulta não encontrada"));

        if (sessaoRepository.existsByDataSessao(novaDataSessao)){
            throw new DataIntegrityViolationException("Já existe uma consulta agendada para esse horário");
        }

        sessao.setDataSessao(novaDataSessao);
    }
}

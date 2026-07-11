package com.example.VieneClinica.service;

import com.example.VieneClinica.dto.procedimento.ProcedimentoDTO;
import com.example.VieneClinica.dto.procedimento.ProcedimentoResponse;
import com.example.VieneClinica.dto.procedimento.UrlDocumento;
import com.example.VieneClinica.entity.Pagamento;
import com.example.VieneClinica.entity.Procedimento;
import com.example.VieneClinica.entity.Prontuario;
import com.example.VieneClinica.entity.Usuario;
import com.example.VieneClinica.mapper.ProcedimentoMapper;
import com.example.VieneClinica.repository.PagamentoRepository;
import com.example.VieneClinica.repository.ProcedimentoRepository;
import com.example.VieneClinica.repository.ProntuarioRepository;
import com.example.VieneClinica.utils.BuscarUsuario;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ProcedimentoService {

    private final ProcedimentoMapper procedimentoMapper;
    private final ProcedimentoRepository procedimentoRepository;
    private final BuscarUsuario buscarUsuario;
    private final ProntuarioRepository prontuarioRepository;
    private final PagamentoRepository pagamentoRepository;

    @PreAuthorize("hasAnyAuthority('ROLE_PROFISSIONAL', 'ROLE_DEV')")
    public ProcedimentoResponse registrarProcedimento(Long prontuarioId, ProcedimentoDTO dto){

        Prontuario prontuario = prontuarioRepository.findById(prontuarioId)
                .orElseThrow(()-> new EntityNotFoundException("Prontuário não encontrado"));

        Usuario usuario = buscarUsuario.buscarUsuario();

        Procedimento procedimento = procedimentoMapper.toEntity(dto);
        procedimento.setProntuario(prontuario);
        procedimento.setUsuario(usuario);

        procedimentoRepository.save(procedimento);

        return procedimentoMapper.toResponse(procedimento);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DEV')")
    public void definirPagamento(Long procedimentoId, Long pagamentoId){

        Procedimento procedimento = procedimentoRepository.findById(procedimentoId)
                .orElseThrow(()-> new EntityNotFoundException("Procedimento não encontrado"));

        Pagamento pagamento = pagamentoRepository.findById(pagamentoId)
                .orElseThrow(()-> new EntityNotFoundException("Pagamento não encontrado"));

        procedimento.setPagamento(pagamento);

        procedimentoRepository.save(procedimento);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DEV')")
    public void definirUrlDocumento(Long procedimentoId, UrlDocumento dto){
        Procedimento procedimento = procedimentoRepository.findById(procedimentoId)
                .orElseThrow(() -> new EntityNotFoundException("Procedimento não encontrado"));

        procedimento.setUrlDocumento(dto.urlDocumento());
        procedimentoRepository.save(procedimento);
    }

}

package com.example.VieneClinica.service;

import com.example.VieneClinica.dto.ProcedimentoDTO;
import com.example.VieneClinica.dto.ProcedimentoResponse;
import com.example.VieneClinica.entity.Procedimento;
import com.example.VieneClinica.entity.Professional;
import com.example.VieneClinica.entity.Prontuario;
import com.example.VieneClinica.mapper.ProcedimentoMapper;
import com.example.VieneClinica.repository.ProcedimentoRepository;
import com.example.VieneClinica.repository.ProfessionalRepository;
import com.example.VieneClinica.repository.ProntuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcedimentoService {

    private final ProcedimentoRepository procedimentoRepository;
    private final ProcedimentoMapper procedimentoMapper;
    private final ProntuarioRepository prontuarioRepository;
    private final ProfessionalRepository professionalRepository;

    public ProcedimentoResponse criarProcedimento( Long prontuarioId,  ProcedimentoDTO dto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String cpf = authentication.getName();

        Professional professional = professionalRepository.findByUserPersonCpf(cpf)
                .orElseThrow(()-> new EntityNotFoundException("Profissional não encontrado"));

        Prontuario prontuario = prontuarioRepository.findById(prontuarioId)
                .orElseThrow(()-> new EntityNotFoundException("Prontuário não encontrado"));


        Procedimento procedimento = procedimentoMapper.toEntity(dto);
        procedimento.setProntuario(prontuario);
        procedimento.setProfessional(professional);

        procedimentoRepository.save(procedimento);

        return procedimentoMapper.toResponse(procedimento);
    }

    public void vincularUrlDocumento(Long procedimentoId, String urlDocumento){

        Procedimento procedimento = procedimentoRepository.findById(procedimentoId)
                .orElseThrow(()-> new EntityNotFoundException("Procedimento não encontrado"));

        if (procedimento.getUrlDocumento() != null){
            throw new IllegalArgumentException("O procedimento já está vinculado a um documento");
        }

        procedimento.setUrlDocumento(urlDocumento);
        procedimentoRepository.save(procedimento);
    }
}

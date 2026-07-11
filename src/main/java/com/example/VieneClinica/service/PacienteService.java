package com.example.VieneClinica.service;

import com.example.VieneClinica.dto.paciente.PacienteDTO;
import com.example.VieneClinica.dto.paciente.PacienteResponse;
import com.example.VieneClinica.dto.paciente.PacienteUpdate;
import com.example.VieneClinica.entity.Paciente;
import com.example.VieneClinica.mapper.PacienteMapper;
import com.example.VieneClinica.repository.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteResponse cadastrarPaciente(PacienteDTO dto){

        if (pacienteRepository.existsByCpf(dto.cpf())){
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        Paciente novoPaciente = pacienteMapper.toEntity(dto);
        pacienteRepository.save(novoPaciente);

        return pacienteMapper.toResponse(novoPaciente);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DEV')")
    public void pacienteUpdate(Long id, PacienteUpdate dto){

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        if (dto.email() != null){
            paciente.setEmail(dto.email());
        }

        if (dto.endereco() != null){
            paciente.setEndereco(dto.endereco());
        }

        if (dto.telefone() != null){
            paciente.setTelefone(dto.telefone());
        }

        pacienteRepository.save(paciente);
    }

}

package com.example.VieneClinica.mapper;

import com.example.VieneClinica.dto.paciente.PacienteDTO;
import com.example.VieneClinica.dto.paciente.PacienteResponse;
import com.example.VieneClinica.entity.Paciente;

public class PacienteMapper{

    public Paciente toEntity(PacienteDTO dto){
        Paciente paciente = new Paciente();
        paciente.setNomeCompleto(dto.nomeCompleto());
        paciente.setEmail(dto.email());
        paciente.setTelefone(dto.telefone());
        paciente.setCpf(dto.cpf());
        paciente.setEndereco(dto.endereco());

        return paciente;
    }

    public PacienteResponse toResponse(Paciente paciente){
        return new PacienteResponse(
                paciente.getId(),
                paciente.getNomeCompleto(),
                paciente.getCreatedAt()
        );
    }
}


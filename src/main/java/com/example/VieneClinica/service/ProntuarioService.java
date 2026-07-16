package com.example.VieneClinica.service;

import com.example.VieneClinica.entity.Patient;
import com.example.VieneClinica.entity.Professional;
import com.example.VieneClinica.entity.Prontuario;
import com.example.VieneClinica.repository.ProfessionalRepository;
import com.example.VieneClinica.repository.ProntuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final ProfessionalRepository professionalRepository;

    // Criação de prontuário de um paciente -> Usei esse método direto no cadastro do paciente : Paciente criado, prontuário criado junto
    public void criarProntuario(Patient patient){
        Prontuario prontuario = new Prontuario();
        prontuario.setPatient(patient);
        prontuarioRepository.save(prontuario);
    }

    // Vincular profissional à um prontuário
    public void vincularProfissional(Long prontuarioId, Long professionalId){
        Prontuario prontuario = prontuarioRepository.findById(prontuarioId)
                .orElseThrow(()-> new EntityNotFoundException("Prontuário inválido ou não encontrado"));

        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow(()-> new EntityNotFoundException("Profissional não encontrado"));

        prontuario.setProfessional(professional);
        prontuarioRepository.save(prontuario);

    }
}

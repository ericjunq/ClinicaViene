package com.example.VieneClinica.service;


import com.example.VieneClinica.entity.Paciente;
import com.example.VieneClinica.entity.Prontuario;
import com.example.VieneClinica.entity.Usuario;
import com.example.VieneClinica.repository.PacienteRepository;
import com.example.VieneClinica.repository.ProntuarioRepository;
import com.example.VieneClinica.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DEV')")
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final UsuarioRepository usuarioRepository;

    public Prontuario criarProntuario(Long pacienteId, Long profissionalId){

        Usuario usuario = usuarioRepository.findById(profissionalId)
                .orElseThrow(()-> new EntityNotFoundException("Profissional não encontrado"));

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        Prontuario prontuario = new Prontuario();
        prontuario.setPaciente(paciente);
        prontuario.setUsuario(usuario);

        prontuarioRepository.save(prontuario);

        return prontuario;
    }


    public void alterarProfissionalResponsavel(Long prontuarioId, Long profissionalId){

        Usuario usuario = usuarioRepository.findById(profissionalId)
                .orElseThrow(()-> new EntityNotFoundException("Profissional não encontrado"));

        Prontuario prontuario = prontuarioRepository.findById(prontuarioId)
                .orElseThrow(()-> new EntityNotFoundException("Prontuário não encontrado"));

        prontuario.setUsuario(usuario);

        prontuarioRepository.save(prontuario);
    }
}

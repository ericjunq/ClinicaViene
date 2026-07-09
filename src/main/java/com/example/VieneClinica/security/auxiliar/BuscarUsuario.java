package com.example.VieneClinica.security.auxiliar;

import com.example.VieneClinica.dto.procedimento.ProcedimentoDTO;
import com.example.VieneClinica.dto.procedimento.ProcedimentoResponse;
import com.example.VieneClinica.entity.Usuario;
import com.example.VieneClinica.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class BuscarUsuario {

    private final UsuarioRepository usuarioRepository;

    public Usuario buscarUsuario() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        String cpf = authentication.getName();

        return usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

    }
}

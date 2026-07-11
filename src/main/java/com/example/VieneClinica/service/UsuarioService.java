package com.example.VieneClinica.service;

import com.example.VieneClinica.dto.usuario.UsuarioDTO;
import com.example.VieneClinica.dto.usuario.UsuarioResponse;
import com.example.VieneClinica.dto.usuario.UsuarioUpdate;
import com.example.VieneClinica.entity.Usuario;
import com.example.VieneClinica.enums.CargoEnum;
import com.example.VieneClinica.enums.StatusAtividade;
import com.example.VieneClinica.mapper.UsuarioMapper;
import com.example.VieneClinica.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioResponse criarUsuario(UsuarioDTO dto){

        if (usuarioRepository.existsByCpf(dto.cpf())){
            throw new IllegalArgumentException("CPF já cadastrado");
        }

        if (usuarioRepository.existsByEmail(dto.email())){
            throw new IllegalArgumentException("Email já cadastrado");
        }

        if (usuarioRepository.existsByTelefone(dto.telefone())){
            throw new IllegalArgumentException("Telefone já cadastrado");
        }

        if (usuarioRepository.existsByCro(dto.cro())){
            throw new IllegalArgumentException("CRO já registrado");
        }

        Usuario novoUsuario = usuarioMapper.toEntity(dto);
        novoUsuario.setSenhaHash(passwordEncoder.encode(dto.senha()));
        novoUsuario.setStatus(StatusAtividade.ATIVO);

        usuarioRepository.save(novoUsuario);

        return usuarioMapper.toResponse(novoUsuario);
    }

    @PreAuthorize("isAuthenticated()")
    public void usuarioUpdate(Long usuarioId, UsuarioUpdate dto){

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (dto.email() != null){
            if (usuarioRepository.existsByEmail(dto.email())){
                throw new IllegalArgumentException("Email já cadastrado");
            }
            usuario.setEmail(dto.email());
        }

        if (dto.telefone() != null){
            if (usuarioRepository.existsByTelefone(dto.telefone())){
                throw new IllegalArgumentException("Telefone já cadastrado");
            }
            usuario.setTelefone(dto.telefone());
        }

        if (dto.senha() != null){
            usuario.setSenhaHash(passwordEncoder.encode(dto.senha()));
        }

        usuarioRepository.save(usuario);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DEV')")
    public void statusProfissional(Long profissionalId, StatusAtividade status){
        Usuario usuario = usuarioRepository.findById(profissionalId)
                .orElseThrow(()-> new EntityNotFoundException("Profissional não encontrado"));

        if (usuario.getCargo() != CargoEnum.ROLE_PROFISSIONAL){
            throw new AccessDeniedException("Sem permissão para a ação");
        }

        usuario.setStatus(status);

        usuarioRepository.save(usuario);
    }

    @PreAuthorize("hasAuthority('ROLE_DEV')")
    public void statusFuncionarios(Long funcionarioId, StatusAtividade status){
        Usuario usuario = usuarioRepository.findById(funcionarioId)
                .orElseThrow(()-> new EntityNotFoundException("Profissional não encontrado"));

        usuario.setStatus(status);

        usuarioRepository.save(usuario);
    }
}

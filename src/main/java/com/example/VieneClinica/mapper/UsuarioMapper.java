package com.example.VieneClinica.mapper;

import com.example.VieneClinica.dto.usuario.UsuarioDTO;
import com.example.VieneClinica.dto.usuario.UsuarioResponse;
import com.example.VieneClinica.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioDTO dto){
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(dto.nomeCompleto());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());
        usuario.setCro(dto.cro());

        return usuario;
    }

    public UsuarioResponse toResponse(Usuario usuario){
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getCreatedAt()
        );
    }
}

package com.example.VieneClinica.repository;

import com.example.VieneClinica.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);
    boolean existsByCro(String cro);

    Optional<Usuario> findByCpf(String cpf);
}

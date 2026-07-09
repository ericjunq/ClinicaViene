package com.example.VieneClinica.entity;

import com.example.VieneClinica.enums.CargoEnum;
import com.example.VieneClinica.enums.StatusAtividade;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nomeCompleto;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Size(max = 255)
    private String senhaHash;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(nullable = false, unique = true, length = 11)
    private String telefone;

    @Column(nullable = false, unique = true, length = 15)
    private String cro;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CargoEnum cargo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusAtividade status;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    // UserDetails methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(cargo.name()));
    }

    @Override
    public String getPassword() {
        return this.senhaHash;
    }

    @Override
    public String getUsername() {
        return this.cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == StatusAtividade.ATIVO;
    }


}

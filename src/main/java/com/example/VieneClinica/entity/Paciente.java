package com.example.VieneClinica.entity;

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

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(nullable = false, length = 120)
    private String nomeCompleto;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(nullable = false, length = 11)
    private String telefone;


    @Email
    @Column(nullable = false)
    private String email;

    @Size(max = 150)
    private String endereco;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;

}

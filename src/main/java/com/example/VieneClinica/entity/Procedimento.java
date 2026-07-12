package com.example.VieneClinica.entity;

import com.example.VieneClinica.enums.CategoriaAtendimento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "procedimentos")
public class Procedimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 240)
    private String descricao;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "prontuario_id", nullable = false)
    private Prontuario prontuario;


    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String urlDocumento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaAtendimento categoriaAtendimento;

    @CreationTimestamp
    private OffsetDateTime createdAt;
}

package com.example.VieneClinica.entity;

import com.example.VieneClinica.enums.CategoriaAtendimento;
import com.example.VieneClinica.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "procedimento")
public class Procedimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 170)
    private String descricao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prontuario_id")
    private Prontuario prontuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "professional_id")
    private Professional professional;

    @OneToOne
    @JoinColumn(name = "pagamento_id")
    private Pagamento pagamento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoriaAtendimento categoriaAtendimento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;

    @CreationTimestamp
    private OffsetDateTime createdAt;
}

package com.example.VieneClinica.entity;

import com.example.VieneClinica.enums.FormaPagamento;
import com.example.VieneClinica.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "procedimento_id", nullable = false, unique = true)
    private Procedimento procedimento;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    private BigDecimal desconto;

    @Column(nullable = false)
    private BigDecimal valorFinal;

    @Column(nullable = false)
    private BigDecimal valorClinica;

    @Column(nullable = false)
    private BigDecimal valorProfissional;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento statusPagamento;

    @Column(nullable = false)
    private Integer quantidadeParcelas;
}

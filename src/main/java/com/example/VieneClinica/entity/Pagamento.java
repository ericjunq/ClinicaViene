package com.example.VieneClinica.entity;

import com.example.VieneClinica.enums.FormaPagamento;
import com.example.VieneClinica.enums.StatusPagamento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valorFinalBruto;

    private BigDecimal desconto;

    @Column(nullable = false)
    private BigDecimal valorFinalLiquido;

    @Column(nullable = false)
    private BigDecimal valorClinica;

    @Column(nullable = false)
    private BigDecimal valorProfissional;

    @Column(nullable = false)
    private Integer totalParcelas;

    @OneToOne(optional = false)
    @JoinColumn(name = "procedimento_id")
    private Procedimento procedimento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Column(nullable = false)
    private StatusPagamento statusPagamento;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}

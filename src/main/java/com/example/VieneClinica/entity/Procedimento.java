package com.example.VieneClinica.entity;

import com.example.VieneClinica.entity.enums.CategoriasAtendimento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Getter
@Table(name = "procedimentos")
public class Procedimento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false, length = 240)
    private String descricao;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "prontuario_id", nullable = false)
    private Prontuario prontuario;


    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Lob
    @NotNull
    private String assinaturaProfissional;

    @Lob
    @NotNull
    private String assinaturaPaciente;

    @OneToOne(mappedBy = "pagamento_id", cascade = CascadeType.PERSIST)
    private Pagamento pagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriasAtendimento categoriasAtendimento;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public void setCategoriasAtendimento(CategoriasAtendimento categoriasAtendimento) {
        this.categoriasAtendimento = categoriasAtendimento;
    }

    public Procedimento(String descricao, Prontuario prontuario, Usuario usuario, String assinaturaProfissional, String assinaturaPaciente, Pagamento pagamento, CategoriasAtendimento categoriasAtendimento) {
        this.descricao = descricao;
        this.prontuario = prontuario;
        this.usuario = usuario;
        this.assinaturaProfissional = assinaturaProfissional;
        this.assinaturaPaciente = assinaturaPaciente;
        this.pagamento = pagamento;
        this.categoriasAtendimento = categoriasAtendimento;
    }
}

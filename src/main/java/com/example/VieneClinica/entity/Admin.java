package com.example.VieneClinica.entity;


import com.example.VieneClinica.enums.StatusAtividade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "person_id")
    private Person person;

    @Column(nullable = false)
    private String senhaHash;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusAtividade status;



}

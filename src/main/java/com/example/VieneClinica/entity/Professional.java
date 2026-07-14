package com.example.VieneClinica.entity;

import com.example.VieneClinica.enums.AreaAtendimento;
import com.example.VieneClinica.enums.StatusAtividade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "professional")
public class Professional {

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
        private AreaAtendimento areaAtendimento;

        @Column(nullable = false)
        private String registroProfissional;

        @Column(nullable = false)
        @Enumerated(EnumType.STRING)
        private StatusAtividade status;




}

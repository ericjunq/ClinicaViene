package com.example.VieneClinica.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
public class Patient{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "person_id")
    private Person person;

    @CreationTimestamp
    OffsetDateTime created;

}

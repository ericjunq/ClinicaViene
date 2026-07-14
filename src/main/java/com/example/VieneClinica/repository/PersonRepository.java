package com.example.VieneClinica.repository;

import com.example.VieneClinica.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    boolean existsByCpf(String cpf);
}

package com.example.VieneClinica.service;

import com.example.VieneClinica.dto.PersonDTO;
import com.example.VieneClinica.entity.Patient;
import com.example.VieneClinica.entity.Person;
import com.example.VieneClinica.mapper.PersonMapper;
import com.example.VieneClinica.repository.PatientRepository;
import com.example.VieneClinica.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;
    private final PatientRepository patientRepository;
    private final ProntuarioService prontuarioService;

    public Patient criarPacient(PersonDTO dto){
        if (personRepository.existsByCpf(dto.cpf())){
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        if (dto.dataNascimento().isAfter(OffsetDateTime.now())){
            throw new IllegalArgumentException("Data inválida");
        }

        Person person = personMapper.toEntity(dto);
        personRepository.save(person);

        Patient patient = new Patient();
        patient.setPerson(person);

        patientRepository.save(patient);

        // Criação de um prontuário pro paciente
        prontuarioService.criarProntuario(patient);

        return patient;
    }
}

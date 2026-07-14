package com.example.VieneClinica.mapper;

import com.example.VieneClinica.dto.PersonDTO;
import com.example.VieneClinica.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person toEntity(PersonDTO dto){
        Person person = new Person();
        person.setNomeCompleto(dto.nomeCompleto());
        person.setEmail(dto.email());
        person.setTelefone(dto.telefone());
        person.setCpf(dto.cpf());
        person.setEndereco(dto.endereco());
        person.setDataNascimento(dto.dataNascimento());
        return person;
    }
}

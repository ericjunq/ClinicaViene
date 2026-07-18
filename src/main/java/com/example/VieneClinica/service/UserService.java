package com.example.VieneClinica.service;

import com.example.VieneClinica.dto.PersonDTO;
import com.example.VieneClinica.entity.Person;
import com.example.VieneClinica.entity.User;
import com.example.VieneClinica.enums.StatusAtividade;
import com.example.VieneClinica.mapper.PersonMapper;
import com.example.VieneClinica.repository.PersonRepository;
import com.example.VieneClinica.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PersonRepository personRepository;
    private final UserRepository userRepository;
    private final PersonMapper personMapper;
    private final PasswordEncoder passwordEncoder;

    public User criarUser(PersonDTO dto, String senha){
        if (personRepository.existsByCpf(dto.cpf())){
            throw new IllegalArgumentException("CPF já cadastrado");
        }
        if (dto.dataNascimento().isAfter(OffsetDateTime.now())){
            throw new IllegalArgumentException("Data inválida");
        }

        Person person = personMapper.toEntity(dto);
        personRepository.save(person);

        User user = new User();

        user.setPerson(person);
        user.setSenhaHash(passwordEncoder.encode(senha));
        user.setStatus(StatusAtividade.ATIVO);
        userRepository.save(user);

        return user;
    }
}

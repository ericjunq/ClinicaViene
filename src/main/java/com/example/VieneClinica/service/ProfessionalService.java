package com.example.VieneClinica.service;

import com.example.VieneClinica.dto.ProfessionalDTO;
import com.example.VieneClinica.entity.Professional;
import com.example.VieneClinica.entity.User;
import com.example.VieneClinica.mapper.ProfessionalMapper;
import com.example.VieneClinica.repository.ProfessionalRepository;
import com.example.VieneClinica.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final ProfessionalMapper professionalMapper;
    private final UserRepository userRepository;

    public Professional criarProfessional(User user, ProfessionalDTO dto){

        Professional professional = professionalMapper.toEntity(dto);
        professional.setUser(user);
        professionalRepository.save(professional);

        return professional;

    }
}

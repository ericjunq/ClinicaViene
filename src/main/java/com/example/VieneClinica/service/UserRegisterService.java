package com.example.VieneClinica.service;

import com.example.VieneClinica.dto.PersonDTO;
import com.example.VieneClinica.dto.ProfessionalDTO;
import com.example.VieneClinica.entity.Person;
import com.example.VieneClinica.entity.Role;
import com.example.VieneClinica.entity.User;
import com.example.VieneClinica.enums.RoleName;
import com.example.VieneClinica.enums.StatusAtividade;
import com.example.VieneClinica.mapper.PersonMapper;
import com.example.VieneClinica.repository.PersonRepository;
import com.example.VieneClinica.repository.RoleRepository;
import com.example.VieneClinica.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ProfessionalService professionalService;
    private final UserService userService;
    private final AdminService adminService;

    public User criarUser(PersonDTO dto, String senha, RoleName tipoCadastro, ProfessionalDTO professionalDTO) {
        
        User user = userService.criarUser(dto, senha);
        Role role = roleRepository.findByRole(tipoCadastro)
                        .orElseThrow(()-> new EntityNotFoundException("Role inváida"));

        user.setRole(role);

        userRepository.save(user);

        // Cria entity na table relacionada ao cargo
        switch (tipoCadastro) {
            case PROFESSIONAL -> professionalService.criarProfessional(user, professionalDTO);
            case ADMIN -> adminService.criarAdmin(user);
        }

        return user;
    }
}

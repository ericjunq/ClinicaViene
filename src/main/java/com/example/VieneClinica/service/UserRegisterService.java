package com.example.VieneClinica.service;

import com.example.VieneClinica.dto.AdminResponse;
import com.example.VieneClinica.dto.PersonDTO;
import com.example.VieneClinica.dto.ProfessionalDTO;
import com.example.VieneClinica.dto.ProfessionalResponse;
import com.example.VieneClinica.entity.Professional;
import com.example.VieneClinica.entity.Role;
import com.example.VieneClinica.entity.User;
import com.example.VieneClinica.enums.RoleName;
import com.example.VieneClinica.mapper.AdminMapper;
import com.example.VieneClinica.mapper.ProfessionalMapper;
import com.example.VieneClinica.repository.RoleRepository;
import com.example.VieneClinica.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRegisterService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ProfessionalService professionalService;
    private final UserService userService;
    private final AdminService adminService;
    private final AdminMapper adminMapper;
    private final ProfessionalMapper professionalMapper;

    public ProfessionalResponse criarProfessional(PersonDTO dto, String senha, ProfessionalDTO professionalDTO) {
        
        User user = userService.criarUser(dto, senha);
        Role role = roleRepository.findByRole(RoleName.PROFESSIONAL)
                        .orElseThrow(()-> new EntityNotFoundException("Role inváida"));

        user.setRole(role);
        userRepository.save(user);

        Professional professional = professionalService.criarProfessional(user, professionalDTO);

       return professionalMapper.toResponse(user, professional);
    }

    public AdminResponse criarAdmin(PersonDTO dto, String senha) {

        User user = userService.criarUser(dto, senha);
        Role role = roleRepository.findByRole(RoleName.ADMIN)
                .orElseThrow(()-> new EntityNotFoundException("Role inváida"));

        user.setRole(role);
        userRepository.save(user);

        adminService.criarAdmin(user);


        return adminMapper.toResponse(user);
    }

}

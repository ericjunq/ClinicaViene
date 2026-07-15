package com.example.VieneClinica.service;

import com.example.VieneClinica.entity.Admin;
import com.example.VieneClinica.entity.User;
import com.example.VieneClinica.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    public void criarAdmin(User user){
        Admin admin = new Admin();
        admin.setUser(user);
        adminRepository.save(admin);
    }
}

package com.example.VieneClinica.mapper;

import com.example.VieneClinica.dto.AdminResponse;
import com.example.VieneClinica.entity.User;

public class AdminMapper {

    public AdminResponse toResponse(User user){
        return new AdminResponse(
                user.getId(),
                user.getPerson().getNomeCompleto(),
                user.getRole().getRole(),
                user.getCreatedAt()
        );
    }
}

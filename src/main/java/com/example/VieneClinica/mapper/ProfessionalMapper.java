package com.example.VieneClinica.mapper;

import com.example.VieneClinica.dto.ProfessionalDTO;
import com.example.VieneClinica.dto.ProfessionalResponse;
import com.example.VieneClinica.entity.Professional;
import com.example.VieneClinica.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalMapper {

    public Professional toEntity(ProfessionalDTO dto){
        Professional professional = new Professional();
        professional.setAreaAtendimento(dto.areaAtendimento());
        professional.setRegistroProfissional(dto.registroProfissional());

        return professional;
    }

    public ProfessionalResponse toResponse(User user, Professional professional){
        return new ProfessionalResponse(
                user.getId(),
                user.getPerson().getNomeCompleto(),
                user.getRole().getRole(),
                professional.getAreaAtendimento(),
                professional.getRegistroProfissional(),
                user.getCreatedAt()
        );
    }
}

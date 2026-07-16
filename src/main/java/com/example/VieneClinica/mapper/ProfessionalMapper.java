package com.example.VieneClinica.mapper;

import com.example.VieneClinica.dto.ProfessionalDTO;
import com.example.VieneClinica.entity.Professional;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalMapper {

    public Professional toEntity(ProfessionalDTO dto){
        Professional professional = new Professional();
        professional.setAreaAtendimento(dto.areaAtendimento());
        professional.setRegistroProfissional(dto.registroProfissional());

        return professional;
    }
}

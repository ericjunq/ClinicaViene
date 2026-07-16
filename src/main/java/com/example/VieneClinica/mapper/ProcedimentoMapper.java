package com.example.VieneClinica.mapper;

import com.example.VieneClinica.dto.ProcedimentoDTO;
import com.example.VieneClinica.dto.ProcedimentoResponse;
import com.example.VieneClinica.entity.Procedimento;
import org.springframework.stereotype.Component;

@Component
public class ProcedimentoMapper {

    public Procedimento toEntity(ProcedimentoDTO dto){
        Procedimento procedimento = new Procedimento();
        procedimento.setDescricao(dto.descricao());
        procedimento.setCategoriaAtendimento(dto.categoriaAtendimento());
        return procedimento;
    }

    public ProcedimentoResponse toResponse(Procedimento procedimento){
        return new ProcedimentoResponse(
                procedimento.getId(),
                procedimento.getProntuario().getId(),
                procedimento.getProfessional().getId(),
                procedimento.getDescricao(),
                procedimento.getCategoriaAtendimento(),
                procedimento.getCreatedAt()
        );
    }
}

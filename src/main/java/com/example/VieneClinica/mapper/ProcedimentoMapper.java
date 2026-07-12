package com.example.VieneClinica.mapper;

import com.example.VieneClinica.dto.procedimento.ProcedimentoDTO;
import com.example.VieneClinica.dto.procedimento.ProcedimentoResponse;
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
                procedimento.getDescricao(),
                procedimento.getProntuario().getId(),
                procedimento.getUsuario().getId(),
                procedimento.getCategoriaAtendimento(),
                procedimento.getCreatedAt()
        );
    }
}

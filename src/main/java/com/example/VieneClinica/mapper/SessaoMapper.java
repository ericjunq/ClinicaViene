package com.example.VieneClinica.mapper;

import com.example.VieneClinica.dto.sessao.SessaoDTO;
import com.example.VieneClinica.dto.sessao.SessaoResponse;
import com.example.VieneClinica.entity.Sessao;
import org.springframework.stereotype.Component;

@Component
public class SessaoMapper {

    public Sessao toEntity(SessaoDTO dto){
        Sessao sessao = new Sessao();
        sessao.setDataSessao(dto.dataSessao());
        sessao.setDescricao(dto.descricao());

        return sessao;
    }

    public SessaoResponse toResponse (Sessao sessao){
        return new SessaoResponse(
                sessao.getId(),
                sessao.getUsuario().getId(),
                sessao.getPaciente().getId(),
                sessao.getDataSessao(),
                sessao.getDescricao(),
                sessao.getStatus()
        );
    }
}

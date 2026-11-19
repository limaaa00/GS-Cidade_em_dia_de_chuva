package br.com.fiap.lucas550255.mapper;

import br.com.fiap.lucas550255.domain.Leitura;
import br.com.fiap.lucas550255.domain.Trecho;
import br.com.fiap.lucas550255.dto.LeituraDTO;
import org.springframework.stereotype.Component;

@Component
public class LeituraMapper {


    public Leitura toEntity(LeituraDTO dto, Trecho trecho) {
        Leitura leitura = new Leitura();
        atualizar(leitura, dto, trecho);
        return leitura;
    }

    public void atualizar(Leitura leitura, LeituraDTO dto, Trecho trecho) {
        leitura.setDataHora(dto.getDataHora());
        leitura.setLaminaAgua(dto.getLaminaAgua());
        leitura.setTrecho(trecho);
    }

    public LeituraDTO toDTO(Leitura leitura) {
        LeituraDTO dto = new LeituraDTO();
        dto.setId(leitura.getId());
        dto.setDataHora(leitura.getDataHora());
        dto.setLaminaAgua(leitura.getLaminaAgua());
        dto.setSituacao(leitura.getSituacao());
        dto.setTrechoId(leitura.getTrecho().getId());
        dto.setTrechoLogradouro(leitura.getTrecho().getLogradouro());
        return dto;
    }
}
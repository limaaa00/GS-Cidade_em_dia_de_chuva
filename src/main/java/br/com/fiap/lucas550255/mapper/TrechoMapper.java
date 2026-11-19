package br.com.fiap.lucas550255.mapper;

import br.com.fiap.lucas550255.domain.Bairro;
import br.com.fiap.lucas550255.domain.Trecho;
import br.com.fiap.lucas550255.dto.TrechoDTO;
import org.springframework.stereotype.Component;

@Component
public class TrechoMapper {

    public Trecho toEntity(TrechoDTO dto, Bairro bairro) {
        Trecho trecho = new Trecho();
        atualizar(trecho, dto, bairro);
        return trecho;
    }

    public void atualizar(Trecho trecho, TrechoDTO dto, Bairro bairro) {
        trecho.setLogradouro(dto.getLogradouro().trim());
        trecho.setTipoCalcada(dto.getTipoCalcada());
        trecho.setAltitude(dto.getAltitude());
        trecho.setBairro(bairro);
    }

    public TrechoDTO toDTO(Trecho trecho) {
        TrechoDTO dto = new TrechoDTO();
        dto.setId(trecho.getId());
        dto.setLogradouro(trecho.getLogradouro());
        dto.setTipoCalcada(trecho.getTipoCalcada());
        dto.setAltitude(trecho.getAltitude());
        dto.setBairroId(trecho.getBairro().getId());
        dto.setBairroNome(trecho.getBairro().getNome());
        return dto;
    }
}
package br.com.fiap.lucas550255.mapper;

import br.com.fiap.lucas550255.domain.Bairro;
import br.com.fiap.lucas550255.dto.BairroDTO;
import org.springframework.stereotype.Component;

@Component
public class BairroMapper {

    public Bairro toEntity(BairroDTO dto) {
        Bairro bairro = new Bairro();
        atualizar(bairro, dto);
        return bairro;
    }


    public void atualizar(Bairro bairro, BairroDTO dto) {
        bairro.setNome(dto.getNome().trim());
        bairro.setZona(dto.getZona());
        bairro.setPopulacao(dto.getPopulacao());
    }

    public BairroDTO toDTO(Bairro bairro) {
        BairroDTO dto = new BairroDTO();
        dto.setId(bairro.getId());
        dto.setNome(bairro.getNome());
        dto.setZona(bairro.getZona());
        dto.setPopulacao(bairro.getPopulacao());
        return dto;
    }
}
package br.com.fiap.lucas550255.service;

import br.com.fiap.lucas550255.domain.Bairro;
import br.com.fiap.lucas550255.dto.BairroDTO;
import br.com.fiap.lucas550255.exception.RegraNegocioException;
import br.com.fiap.lucas550255.mapper.BairroMapper;
import br.com.fiap.lucas550255.repository.BairroRepository;
import br.com.fiap.lucas550255.repository.TrechoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BairroService {

    private final BairroRepository bairroRepository;
    private final TrechoRepository trechoRepository;
    private final BairroMapper bairroMapper;

    public BairroService(BairroRepository bairroRepository,
                         TrechoRepository trechoRepository,
                         BairroMapper bairroMapper) {
        this.bairroRepository = bairroRepository;
        this.trechoRepository = trechoRepository;
        this.bairroMapper = bairroMapper;
    }


    @Transactional(readOnly = true)
    public List<BairroDTO> listar() {
        return bairroRepository.findAll(Sort.by(Sort.Order.asc("nome").ignoreCase()))
                .stream().map(bairroMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public BairroDTO buscar(Long id) {
        return bairroMapper.toDTO(obter(id));
    }

    @Transactional
    public BairroDTO incluir(BairroDTO dto) {
        Bairro bairro = bairroMapper.toEntity(dto);
        return bairroMapper.toDTO(bairroRepository.save(bairro));
    }

    @Transactional
    public BairroDTO alterar(Long id, BairroDTO dto) {
        Bairro bairro = obter(id);
        bairroMapper.atualizar(bairro, dto);
        return bairroMapper.toDTO(bairroRepository.save(bairro));
    }


    @Transactional
    public void excluir(Long id) {
        Bairro bairro = obter(id);
        if (trechoRepository.existsByBairroId(id)) {
            throw new RegraNegocioException("Não é possível excluir o bairro \"" + bairro.getNome()
                    + "\": existem trechos cadastrados nele.");
        }
        bairroRepository.delete(bairro);
    }

    private Bairro obter(Long id) {
        return bairroRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Bairro não encontrado."));
    }
}
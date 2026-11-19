package br.com.fiap.lucas550255.service;

import br.com.fiap.lucas550255.domain.Bairro;
import br.com.fiap.lucas550255.domain.Trecho;
import br.com.fiap.lucas550255.dto.TrechoDTO;
import br.com.fiap.lucas550255.exception.RegraNegocioException;
import br.com.fiap.lucas550255.mapper.TrechoMapper;
import br.com.fiap.lucas550255.repository.BairroRepository;
import br.com.fiap.lucas550255.repository.LeituraRepository;
import br.com.fiap.lucas550255.repository.TrechoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrechoService {

    private final TrechoRepository trechoRepository;
    private final BairroRepository bairroRepository;
    private final LeituraRepository leituraRepository;
    private final TrechoMapper trechoMapper;

    public TrechoService(TrechoRepository trechoRepository,
                         BairroRepository bairroRepository,
                         LeituraRepository leituraRepository,
                         TrechoMapper trechoMapper) {
        this.trechoRepository = trechoRepository;
        this.bairroRepository = bairroRepository;
        this.leituraRepository = leituraRepository;
        this.trechoMapper = trechoMapper;
    }

    @Transactional(readOnly = true)
    public List<TrechoDTO> listar() {
        return trechoRepository.findAll(Sort.by(Sort.Order.asc("logradouro").ignoreCase()))
                .stream().map(trechoMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public TrechoDTO buscar(Long id) {
        return trechoMapper.toDTO(obter(id));
    }

    @Transactional
    public TrechoDTO incluir(TrechoDTO dto) {
        Trecho trecho = trechoMapper.toEntity(dto, obterBairro(dto.getBairroId()));
        return trechoMapper.toDTO(trechoRepository.save(trecho));
    }

    @Transactional
    public TrechoDTO alterar(Long id, TrechoDTO dto) {
        Trecho trecho = obter(id);
        trechoMapper.atualizar(trecho, dto, obterBairro(dto.getBairroId()));
        return trechoMapper.toDTO(trechoRepository.save(trecho));
    }


    @Transactional
    public void excluir(Long id) {
        Trecho trecho = obter(id);
        if (leituraRepository.existsByTrechoId(id)) {
            throw new RegraNegocioException("Não é possível excluir o trecho \"" + trecho.getLogradouro()
                    + "\": existem leituras cadastradas nele.");
        }
        trechoRepository.delete(trecho);
    }

    private Trecho obter(Long id) {
        return trechoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Trecho não encontrado."));
    }

    private Bairro obterBairro(Long bairroId) {
        return bairroRepository.findById(bairroId)
                .orElseThrow(() -> new RegraNegocioException("Bairro não encontrado."));
    }
}
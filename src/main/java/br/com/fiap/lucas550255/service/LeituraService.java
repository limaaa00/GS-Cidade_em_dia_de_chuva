package br.com.fiap.lucas550255.service;

import br.com.fiap.lucas550255.domain.Leitura;
import br.com.fiap.lucas550255.domain.Trecho;
import br.com.fiap.lucas550255.dto.LeituraDTO;
import br.com.fiap.lucas550255.exception.RegraNegocioException;
import br.com.fiap.lucas550255.mapper.LeituraMapper;
import br.com.fiap.lucas550255.repository.LeituraRepository;
import br.com.fiap.lucas550255.repository.TrechoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeituraService {

    private final LeituraRepository leituraRepository;
    private final TrechoRepository trechoRepository;
    private final LeituraMapper leituraMapper;
    private final int limiarAlerta; // parâmetro P2 (L)

    public LeituraService(LeituraRepository leituraRepository,
                          TrechoRepository trechoRepository,
                          LeituraMapper leituraMapper,
                          @Value("${app.limiar-alerta}") int limiarAlerta) {
        this.leituraRepository = leituraRepository;
        this.trechoRepository = trechoRepository;
        this.leituraMapper = leituraMapper;
        this.limiarAlerta = limiarAlerta;
    }


    @Transactional(readOnly = true)
    public List<LeituraDTO> listar() {
        return leituraRepository.findAll(Sort.by(Sort.Direction.DESC, "dataHora"))
                .stream().map(leituraMapper::toDTO).toList();
    }

    @Transactional(readOnly = true)
    public LeituraDTO buscar(Long id) {
        return leituraMapper.toDTO(obter(id));
    }

    @Transactional
    public LeituraDTO incluir(LeituraDTO dto) {
        Leitura leitura = leituraMapper.toEntity(dto, obterTrecho(dto.getTrechoId()));
        leitura.setSituacao(calcularSituacao(leitura.getLaminaAgua()));
        return leituraMapper.toDTO(leituraRepository.save(leitura));
    }

    @Transactional
    public LeituraDTO alterar(Long id, LeituraDTO dto) {
        Leitura leitura = obter(id);
        leituraMapper.atualizar(leitura, dto, obterTrecho(dto.getTrechoId()));
        leitura.setSituacao(calcularSituacao(leitura.getLaminaAgua())); // recalcula (R1)
        return leituraMapper.toDTO(leituraRepository.save(leitura));
    }

    @Transactional
    public void excluir(Long id) {
        leituraRepository.delete(obter(id));
    }


    private String calcularSituacao(double lamina) {
        if (lamina >= limiarAlerta) {
            return "ALERTA";
        }
        if (lamina >= limiarAlerta / 2.0) {
            return "ATENCAO";
        }
        return "NORMAL";
    }

    private Leitura obter(Long id) {
        return leituraRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Leitura não encontrada."));
    }

    private Trecho obterTrecho(Long trechoId) {
        return trechoRepository.findById(trechoId)
                .orElseThrow(() -> new RegraNegocioException("Trecho não encontrado."));
    }
}
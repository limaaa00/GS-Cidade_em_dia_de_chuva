package br.com.fiap.lucas550255.repository;

import br.com.fiap.lucas550255.domain.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeituraRepository extends JpaRepository<Leitura, Long> {


    boolean existsByTrechoId(Long trechoId);
}
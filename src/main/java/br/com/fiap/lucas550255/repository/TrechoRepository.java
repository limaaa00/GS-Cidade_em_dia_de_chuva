package br.com.fiap.lucas550255.repository;

import br.com.fiap.lucas550255.domain.Trecho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  TrechoRepository extends JpaRepository<Trecho, Long> {


    boolean existsByBairroId(Long bairroId);
}
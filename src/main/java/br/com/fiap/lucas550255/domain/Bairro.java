package br.com.fiap.lucas550255.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GS255_BAIRRO")
public class Bairro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 80)
    private String nome;

    @Column(name = "ZONA", nullable = false, length = 20)
    private String zona;

    @Column(name = "POPULACAO", nullable = false)
    private Integer populacao;


    @OneToMany(mappedBy = "bairro")
    private List<Trecho> trechos = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }
    public Integer getPopulacao() { return populacao; }
    public void setPopulacao(Integer populacao) { this.populacao = populacao; }
    public List<Trecho> getTrechos() { return trechos; }
    public void setTrechos(List<Trecho> trechos) { this.trechos = trechos; }
}
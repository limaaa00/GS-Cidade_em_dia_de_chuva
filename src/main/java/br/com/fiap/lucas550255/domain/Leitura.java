package br.com.fiap.lucas550255.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "GS255_LEITURA")
public class Leitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATA_HORA", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "LAMINA_AGUA", nullable = false)
    private Double laminaAgua;


    @Column(name = "SITUACAO", nullable = false, length = 10)
    private String situacao;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TRECHO_ID", nullable = false)
    private Trecho trecho;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public Double getLaminaAgua() { return laminaAgua; }
    public void setLaminaAgua(Double laminaAgua) { this.laminaAgua = laminaAgua; }
    public String getSituacao() { return situacao; }
    public void setSituacao(String situacao) { this.situacao = situacao; }
    public Trecho getTrecho() { return trecho; }
    public void setTrecho(Trecho trecho) { this.trecho = trecho; }
}
package br.com.fiap.lucas550255.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GS255_TRECHO")
public class Trecho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOGRADOURO", nullable = false, length = 120)
    private String logradouro;

    @Column(name = "TIPO_CALCADA", nullable = false, length = 30)
    private String tipoCalcada;

    @Column(name = "ALTITUDE", nullable = false)
    private Double altitude;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "BAIRRO_ID", nullable = false)
    private Bairro bairro;


    @OneToMany(mappedBy = "trecho")
    private List<Leitura> leituras = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public String getTipoCalcada() { return tipoCalcada; }
    public void setTipoCalcada(String tipoCalcada) { this.tipoCalcada = tipoCalcada; }
    public Double getAltitude() { return altitude; }
    public void setAltitude(Double altitude) { this.altitude = altitude; }
    public Bairro getBairro() { return bairro; }
    public void setBairro(Bairro bairro) { this.bairro = bairro; }
    public List<Leitura> getLeituras() { return leituras; }
    public void setLeituras(List<Leitura> leituras) { this.leituras = leituras; }
}
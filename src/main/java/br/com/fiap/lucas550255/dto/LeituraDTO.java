package br.com.fiap.lucas550255.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class LeituraDTO {

    private Long id;

    @NotNull(message = "A data e a hora são obrigatórias.")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataHora;

    @NotNull(message = "A lâmina de água é obrigatória.")
    @DecimalMin(value = "0", message = "A lâmina de água deve estar entre 0 e 300 cm.")
    @DecimalMax(value = "300", message = "A lâmina de água deve estar entre 0 e 300 cm.")
    private Double laminaAgua;

    @NotNull(message = "O trecho é obrigatório.")
    private Long trechoId;


    private String situacao;
    private String trechoLogradouro;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public Double getLaminaAgua() { return laminaAgua; }
    public void setLaminaAgua(Double laminaAgua) { this.laminaAgua = laminaAgua; }
    public Long getTrechoId() { return trechoId; }
    public void setTrechoId(Long trechoId) { this.trechoId = trechoId; }
    public String getSituacao() { return situacao; }
    public void setSituacao(String situacao) { this.situacao = situacao; }
    public String getTrechoLogradouro() { return trechoLogradouro; }
    public void setTrechoLogradouro(String trechoLogradouro) { this.trechoLogradouro = trechoLogradouro; }
}
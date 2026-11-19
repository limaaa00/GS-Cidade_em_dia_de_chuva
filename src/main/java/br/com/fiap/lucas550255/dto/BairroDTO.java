package br.com.fiap.lucas550255.dto;

import jakarta.validation.constraints.*;

public class BairroDTO {

    private Long id;

    @NotBlank(message = "O nome do bairro é obrigatório.")
    @Size(max = 80, message = "O nome do bairro deve ter no máximo 80 caracteres.")
    private String nome;

    @NotBlank(message = "A zona é obrigatória.")
    @Pattern(regexp = ValoresPermitidos.ZONAS_REGEX,
            message = "Zona inválida. Use Centro, Norte, Sul, Leste ou Oeste.")
    private String zona;

    @NotNull(message = "A população é obrigatória.")
    @Min(value = 0, message = "A população deve ser maior ou igual a zero.")
    private Integer populacao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getZona() { return zona; }
    public void setZona(String zona) { this.zona = zona; }
    public Integer getPopulacao() { return populacao; }
    public void setPopulacao(Integer populacao) { this.populacao = populacao; }
}
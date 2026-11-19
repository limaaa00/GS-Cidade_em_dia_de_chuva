package br.com.fiap.lucas550255.dto;

import jakarta.validation.constraints.*;

public class TrechoDTO {

    private Long id;

    @NotBlank(message = "O logradouro é obrigatório.")
    @Size(max = 120, message = "O logradouro deve ter no máximo 120 caracteres.")
    private String logradouro;

    @NotBlank(message = "O tipo de calçada é obrigatório.")
    @Pattern(regexp = ValoresPermitidos.TIPOS_CALCADA_REGEX,
            message = "Tipo de calçada inválido. Use Regular, Irregular, Escadaria ou Sem iluminação.")
    private String tipoCalcada;

    @NotNull(message = "A altitude é obrigatória.")
    @DecimalMin(value = "0", message = "A altitude deve estar entre 0 e 2000 m.")
    @DecimalMax(value = "2000", message = "A altitude deve estar entre 0 e 2000 m.")
    private Double altitude;

    @NotNull(message = "O bairro é obrigatório.")
    private Long bairroId;


    private String bairroNome;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLogradouro() { return logradouro; }
    public void setLogradouro(String logradouro) { this.logradouro = logradouro; }
    public String getTipoCalcada() { return tipoCalcada; }
    public void setTipoCalcada(String tipoCalcada) { this.tipoCalcada = tipoCalcada; }
    public Double getAltitude() { return altitude; }
    public void setAltitude(Double altitude) { this.altitude = altitude; }
    public Long getBairroId() { return bairroId; }
    public void setBairroId(Long bairroId) { this.bairroId = bairroId; }
    public String getBairroNome() { return bairroNome; }
    public void setBairroNome(String bairroNome) { this.bairroNome = bairroNome; }
}
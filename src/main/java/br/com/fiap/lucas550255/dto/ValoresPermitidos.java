package br.com.fiap.lucas550255.dto;

import java.util.List;


public final class ValoresPermitidos {

    public static final String ZONAS_REGEX = "Centro|Norte|Sul|Leste|Oeste";
    public static final String TIPOS_CALCADA_REGEX = "Regular|Irregular|Escadaria|Sem iluminação";

    public static final List<String> ZONAS = List.of("Centro", "Norte", "Sul", "Leste", "Oeste");
    public static final List<String> TIPOS_CALCADA = List.of("Regular", "Irregular", "Escadaria", "Sem iluminação");

    private ValoresPermitidos() {
    }
}
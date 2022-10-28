package ar.edu.uade.server.model.enums;

import lombok.Getter;

import java.util.Arrays;

public enum TamanioEnum {
    CHICO("Chico"),
    MEDIANO("Mediano"),
    GRANDE("Grande"),
    INDEFINIDO("Indefinido");

    @Getter
    private String nombre;

    TamanioEnum(String nombre) {
        this.nombre = nombre;
    }

    public static TamanioEnum getEnum(String nombre) {
        return Arrays.stream(TamanioEnum.values()).filter(e -> e.getNombre().equals(nombre)).toList().get(0);
    }
}
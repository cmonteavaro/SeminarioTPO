package ar.edu.uade.server.model.enums;

import lombok.Getter;

import java.util.Arrays;


public enum TipoAnimalEnum {
    GATO("Gato"),
    PERRO("Perro");

    @Getter
    private String nombre;

    TipoAnimalEnum (String nombre) {
        this.nombre = nombre;
    }

    public static TipoAnimalEnum getEnum(String nombre) {
        return Arrays.stream(TipoAnimalEnum.values()).filter(e -> e.getNombre().equals(nombre)).toList().get(0);
    }
}
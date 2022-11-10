package ar.edu.uade.server.model.enums;

import lombok.Getter;

/**
 * 
 */
public enum TipoAnimalEnum {
    GATO("Gato"),
    PERRO("Perro");

    @Getter
    private String nombre;

    TipoAnimalEnum (String nombre) {
        this.nombre = nombre;
    }
}
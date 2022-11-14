package ar.edu.uade.server.model.enums;

import lombok.Getter;

import java.util.Arrays;

public enum EstadoPublicacionAnimalEnum {
    DISPONIBLE("Disponible"),
    EN_PROCESO("En proceso"),
    FINALIZADA("Finalizada");

    @Getter
    private String nombre;

    EstadoPublicacionAnimalEnum(String nombre) {
        this.nombre = nombre;
    }

    public static EstadoPublicacionAnimalEnum getEnum(String nombre) {
        return Arrays.stream(EstadoPublicacionAnimalEnum.values()).filter(e -> e.getNombre().equals(nombre)).toList().get(0);
    }
}
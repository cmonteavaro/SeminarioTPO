package ar.edu.uade.server.model;
import ar.edu.uade.server.model.enums.TamanioEnum;
import ar.edu.uade.server.model.enums.TipoAnimalEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Animal {

    private String nombre;
    private TamanioEnum tamanioActual;
    private TamanioEnum tamanioEsperado;
    private Date fechaNac;
    private TipoAnimalEnum tipoAnimal;
    private Boolean castrado;
    private Boolean esquemaCompletoVacunas;
    private Boolean desparasitado;

    public Animal(String nombre, TipoAnimalEnum tipoAnimal) {
        this.nombre = nombre;
        this.tipoAnimal = tipoAnimal;
    }
}
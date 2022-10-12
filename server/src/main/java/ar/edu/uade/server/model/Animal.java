package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Animal {

    /**
     * Default constructor
     */
    public Animal() {
    }

    /**
     * 
     */
    private Tamanio tamanioActual;

    /**
     * 
     */
    private Tamanio tamanioEsperado;

    /**
     * 
     */
    private Date fechaNac;

    /**
     * 
     */
    private String nombre;

    /**
     * 
     */
    private TipoAnimal tipoAnimal;

    /**
     * 
     */
    private Boolean castrado;

    /**
     * 
     */
    private Boolean esquemaCompletoVacunas;

    /**
     * 
     */
    private Boolean desparasitado;

}
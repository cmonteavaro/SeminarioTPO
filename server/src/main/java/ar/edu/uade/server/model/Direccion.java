package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Direccion {

    /**
     * Default constructor
     */
    public Direccion() {
    }

    /**
     * 
     */
    private String calle;

    /**
     * 
     */
    private Integer altura;

    /**
     * 
     */
    private String localidad;

    /**
     * 
     */
    private String codigoPostal;

    /**
     * 
     */
    private String provincia;

    /**
     * 
     */
    private String latitud;

    /**
     * 
     */
    private String longitud;

}
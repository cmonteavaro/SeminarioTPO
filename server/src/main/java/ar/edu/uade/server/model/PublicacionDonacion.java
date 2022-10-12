package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class PublicacionDonacion {

    /**
     * Default constructor
     */
    public PublicacionDonacion() {
    }

    /**
     * 
     */
    private String titulo;

    /**
     * 
     */
    private String descripcion;

    /**
     * 
     */
    private Date fechaPublicacion;

    /**
     * 
     */
    private TipoDonacion tipo;

    /**
     * 
     */
    private Refugio refugio;

}
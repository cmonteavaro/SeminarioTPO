package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class PublicacionVoluntariado {

    /**
     * Default constructor
     */
    public PublicacionVoluntariado() {
    }

    /**
     * 
     */
    private TipoVoluntariado tipo;

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
    private Refugio refugio;

    /**
     * 
     */
    private Date fechaPublicacion;

}
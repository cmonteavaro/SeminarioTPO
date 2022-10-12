package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Refugio {

    /**
     * Default constructor
     */
    public Refugio() {
    }

    /**
     * 
     */
    private String nombre;

    /**
     * 
     */
    private Direccion direccion;

    /**
     * 
     */
    private String usuario;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String responsable;

    /**
     * 
     */
    private Integer telefono;

    /**
     * 
     */
    private List<RedSocial> redesSociales;

    /**
     * 
     */
    private String linkDonacionesMonetarias;

    /**
     * 
     */
    private List<PublicacionAnimal> publicacionesAnimales;

    /**
     * 
     */
    private List<PublicacionVoluntariado> publicacionesVoluntariado;

    /**
     * 
     */
    private List<PublicacionDonacion> publicacionesDonacionesNoMonetarias;

    /**
     * 
     */
    private Integer radioAlcance;

    /**
     * 
     */
    public void Operation1() {
        // TODO implement here
    }

}
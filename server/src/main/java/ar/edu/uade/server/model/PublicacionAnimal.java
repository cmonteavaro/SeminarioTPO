package ar.edu.uade.server.model;
import java.util.*;

/**
 * 
 */
public class PublicacionAnimal {

    /**
     * Default constructor
     */
    public PublicacionAnimal() {
    }

    /**
     * 
     */
    private List<String> galeriaImagenes;

    /**
     * 
     */
    private String descripcion;

    /**
     * 
     */
    private Animal animal;

    /**
     * 
     */
    private Refugio refugio;

    /**
     * 
     */
    private Boolean necesitaPatio;

    /**
     * 
     */
    private Boolean puedeConvivirConInfantes;

    /**
     * 
     */
    private Boolean puedeConvivirConGatos;

    /**
     * 
     */
    private Boolean puedeConvivirConCachorros;

    /**
     * 
     */
    private Boolean puedeConvivirConPerrosAdultos;

    /**
     * 
     */
    private Boolean requiereHogarAmplio;

    /**
     * 
     */
    private Boolean transporteCubierto;

    /**
     * 
     */
    private Date fechaPublicacion;

    /**
     * 
     */
    private EstadoPublicacionAnimal estado;

}
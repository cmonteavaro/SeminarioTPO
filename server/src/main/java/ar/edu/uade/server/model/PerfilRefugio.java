package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.*;
import java.util.List;

@Getter
@Setter
public class PerfilRefugio {

    /**
     * Default constructor
     */
    public PerfilRefugio() {
    }

    /**
     * 
     */
    private List<String> galeriaImagenes;

    /**
     * 
     */
    private String fotoPerfil;

    /**
     * 
     */
    private String banner;

    /**
     * 
     */
    private String descripcionCorta;

    /**
     * 
     */
    private String descripcionLarga;

    /**
     * 
     */
    private Color color;

}
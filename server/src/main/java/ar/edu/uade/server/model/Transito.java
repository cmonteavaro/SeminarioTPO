package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Transito extends PublicacionAnimal {

    /**
     * Default constructor
     */
    public Transito() {
    }

    /**
     * 
     */
    private Boolean gastosAlimentacionCubiertos;

    /**
     * 
     */
    private Boolean gastosMedicosCubiertos;

    /**
     * 
     */
    private Integer duracionMinima;

}
package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.*;

@Getter
@Setter
@Embeddable
public class Direccion {

    private String calle;
    private Integer altura;
    private String localidad;
    private String codigoPostal;
    private String provincia;
    private String latitud;
    private String longitud;

    public Direccion() {
    }

}
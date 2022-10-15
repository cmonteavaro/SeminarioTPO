package ar.edu.uade.server.model;
import ar.edu.uade.server.model.enums.TipoVoluntariadoEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public class PublicacionVoluntariado {

    private TipoVoluntariadoEnum tipo;
    private String titulo;
    private String descripcion;
    private Refugio refugio;
    private LocalDate fechaPublicacion;

    public PublicacionVoluntariado(String titulo, Refugio refugio) {
        this.fechaPublicacion = LocalDate.now();
        this.titulo = titulo;
        this.refugio = refugio;
    }
}
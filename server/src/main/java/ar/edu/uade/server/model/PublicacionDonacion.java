package ar.edu.uade.server.model;
import ar.edu.uade.server.model.enums.TipoDonacionEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
public class PublicacionDonacion {

    private String titulo;
    private String descripcion;
    private TipoDonacionEnum tipo;
    private Refugio refugio;
    private LocalDate fechaPublicacion;

    public PublicacionDonacion(String titulo, Refugio refugio) {
        this.fechaPublicacion = LocalDate.now();
        this.titulo = titulo;
        this.refugio = refugio;
    }
}
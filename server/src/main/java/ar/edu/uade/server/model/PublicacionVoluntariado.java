package ar.edu.uade.server.model;
import ar.edu.uade.server.model.enums.TipoVoluntariadoEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
public class PublicacionVoluntariado {

    @Id
    @GeneratedValue
    private long id;
    @Enumerated
    private TipoVoluntariadoEnum tipo;
    private String titulo;
    private String descripcion;
    private Refugio refugio;
    @Temporal(TemporalType.DATE)
    private LocalDate fechaPublicacion;

    public PublicacionVoluntariado(String titulo, Refugio refugio) {
        this.fechaPublicacion = LocalDate.now();
        this.titulo = titulo;
        this.refugio = refugio;
    }

    public PublicacionVoluntariado() {

    }
}
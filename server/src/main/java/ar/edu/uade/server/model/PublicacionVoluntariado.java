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
    @OneToOne
    private Refugio refugio;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate fechaPublicacion;
    private Boolean estaActiva;

    public PublicacionVoluntariado(String titulo, Refugio refugio) {
        this.fechaPublicacion = LocalDate.now();
        this.titulo = titulo;
        this.refugio = refugio;
        this.estaActiva = true;
    }

    public PublicacionVoluntariado() {
        this.fechaPublicacion = LocalDate.now();
        this.estaActiva = true;
    }
}
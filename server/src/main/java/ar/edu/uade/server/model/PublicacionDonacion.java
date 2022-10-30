package ar.edu.uade.server.model;
import ar.edu.uade.server.model.enums.TipoDonacionEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
public class PublicacionDonacion {

    @Id
    @GeneratedValue
    private long id;
    private String titulo;
    private String descripcion;
    @Enumerated
    private TipoDonacionEnum tipo;
    @OneToOne
    private Refugio refugio;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate fechaPublicacion;
    private Boolean estaActiva;

    public PublicacionDonacion(String titulo, Refugio refugio) {
        this.fechaPublicacion = LocalDate.now();
        this.titulo = titulo;
        this.refugio = refugio;
        this.estaActiva = true;
    }

    public PublicacionDonacion() {
        this.fechaPublicacion = LocalDate.now();
        this.estaActiva = true;
    }
}
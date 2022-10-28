package ar.edu.uade.server.model;

import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;


@Getter
@Setter
@Entity
public abstract class PublicacionAnimal {

    @Id @GeneratedValue
    private Long id;
    private String descripcion;
    @ManyToOne(fetch = FetchType.EAGER)
    private Animal animal;
    @ManyToOne(fetch = FetchType.EAGER)
    private Refugio refugio;
    private Boolean necesitaPatio;
    private Boolean puedeConvivirConInfantes;
    private Boolean puedeConvivirConGatos;
    private Boolean puedeConvivirConCachorros;
    private Boolean puedeConvivirConPerrosAdultos;
    private Boolean requiereHogarAmplio;
    private Boolean transporteCubierto;
    private Boolean esUrgente;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate fechaPublicacion;
    @Enumerated
    private EstadoPublicacionAnimalEnum estado;

    public PublicacionAnimal() {
        fechaPublicacion = LocalDate.now();
    }
}

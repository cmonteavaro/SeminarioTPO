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
    @OneToOne
    private Animal animal;
    @OneToOne(fetch = FetchType.EAGER)
    private Refugio refugio;
    private Boolean necesitaPatio;
    private Boolean puedeConvivirConInfantes;
    private Boolean puedeConvivirConGatos;
    private Boolean puedeConvivirConCachorros;
    private Boolean puedeConvivirConPerrosAdultos;
    private Boolean requiereHogarAmplio;
    private Boolean transporteCubierto;

    private Boolean esUrgente;
    @Temporal(TemporalType.DATE)
    private LocalDate fechaPublicacion;
    @Enumerated
    private EstadoPublicacionAnimalEnum estado;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> galeriaImagenes;

    public PublicacionAnimal() {
        this.galeriaImagenes = new ArrayList<String>();
        fechaPublicacion = LocalDate.now();
    }

    public void agregarImagenes(String ... imagenes) { Collections.addAll(galeriaImagenes, imagenes); }

    public void eliminarImagen(String imagen) { if(this.galeriaImagenes.contains(imagen)) this.galeriaImagenes.remove(imagen); }

}
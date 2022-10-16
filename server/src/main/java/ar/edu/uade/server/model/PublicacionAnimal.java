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

    @Setter
    private String descripcion;
    @Setter
    @OneToOne
    private Animal animal;
    @Setter
    @OneToOne(fetch = FetchType.EAGER)
    private Refugio refugio;
    @Setter
    private Boolean necesitaPatio;
    @Setter
    private Boolean puedeConvivirConInfantes;
    @Setter
    private Boolean puedeConvivirConGatos;
    @Setter
    private Boolean puedeConvivirConCachorros;
    @Setter
    private Boolean puedeConvivirConPerrosAdultos;
    @Setter
    private Boolean requiereHogarAmplio;
    @Setter
    private Boolean transporteCubierto;
    @Setter
    @Temporal(TemporalType.DATE)
    private LocalDate fechaPublicacion;
    @Setter
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
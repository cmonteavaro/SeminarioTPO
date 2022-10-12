package ar.edu.uade.server.model;

import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.*;


@Getter
public abstract class PublicacionAnimal {

    @Setter
    private String descripcion;
    @Setter
    private Animal animal;
    @Setter
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
    private LocalDate fechaPublicacion;
    @Setter
    private EstadoPublicacionAnimalEnum estado;

    private List<String> galeriaImagenes;

    public PublicacionAnimal() {
        this.galeriaImagenes = new ArrayList<String>();
        fechaPublicacion = LocalDate.now();
    }

    public void agregarImagenes(String ... imagenes) { Collections.addAll(galeriaImagenes, imagenes); }

    public void eliminarImagen(String imagen) { if(this.galeriaImagenes.contains(imagen)) this.galeriaImagenes.remove(imagen); }

}
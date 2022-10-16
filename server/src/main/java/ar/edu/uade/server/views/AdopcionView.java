package ar.edu.uade.server.views;
import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.model.Direccion;
import ar.edu.uade.server.model.Refugio;
import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import ar.edu.uade.server.model.enums.TamanioEnum;
import ar.edu.uade.server.model.enums.TipoAnimalEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/*
Forma de uso:

Adopcion a = #Objeto de modelo adopcion
AdopcionView av = AdopcionView.toView(a)

 */

@Getter
@Setter
public class AdopcionView {


    //Animal
    private Animal animal;
    //Refugio
    private Long idRefugio;
    private String nombreRefugio;
    private String fotoPerfilRefugio;
    private Direccion direccionRefugio;

    //Publicacion
    private Long idPublicacion;
    private String descripcion;
    private Boolean necesitaPatio;
    private Boolean puedeConvivirConInfantes;
    private Boolean puedeConvivirConGatos;
    private Boolean puedeConvivirConCachorros;
    private Boolean puedeConvivirConPerrosAdultos;
    private Boolean requiereHogarAmplio;
    private Boolean transporteCubierto;
    private Boolean esUrgente;
    private LocalDate fechaPublicacion;
    private EstadoPublicacionAnimalEnum estadoPublicacion;
    private List<String> galeriaImagenesPublicacion;

    public static AdopcionView toView(Adopcion adopcion){
        AdopcionView view = new AdopcionView();
        //Animal
        view.animal = adopcion.getAnimal();
        //Refugio
        view.idRefugio = adopcion.getRefugio().getId();
        view.nombreRefugio = adopcion.getRefugio().getNombre();
        view.fotoPerfilRefugio = adopcion.getRefugio().getPerfilRefugio().getFotoPerfil();
        view.direccionRefugio = adopcion.getRefugio().getDireccion();
        //Publicacion
        view.idPublicacion = adopcion.getId();
        view.descripcion = adopcion.getDescripcion();
        view.necesitaPatio = adopcion.getNecesitaPatio();
        view.puedeConvivirConInfantes = adopcion.getPuedeConvivirConInfantes();
        view.puedeConvivirConGatos = adopcion.getPuedeConvivirConGatos();
        view.puedeConvivirConCachorros = adopcion.getPuedeConvivirConCachorros();
        view.puedeConvivirConPerrosAdultos = adopcion.getPuedeConvivirConPerrosAdultos();
        view.requiereHogarAmplio = adopcion.getRequiereHogarAmplio();
        view.transporteCubierto = adopcion.getTransporteCubierto();
        view.esUrgente = adopcion.getEsUrgente();
        view.fechaPublicacion = adopcion.getFechaPublicacion();
        view.estadoPublicacion = adopcion.getEstado();
        view.galeriaImagenesPublicacion = adopcion.getGaleriaImagenes();

        return view;
    }

    private AdopcionView(){}
}
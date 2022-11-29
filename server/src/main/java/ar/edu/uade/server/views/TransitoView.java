package ar.edu.uade.server.views;

import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.model.Direccion;
import ar.edu.uade.server.model.Transito;
import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class TransitoView {

    //Animal
    private AnimalView animal;
    //Refugio
    private Long idRefugio;
    private String nombreRefugio;
    private String fotoPerfilRefugio;
    private Direccion direccionRefugio;
    private List<RedSocialView> redesSocialesRefugio;

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
    private String fechaPublicacion;
    private String estadoPublicacion;

    //Transito
    private Boolean gastosAlimentacionCubiertos;
    private Boolean gastosMedicosCubiertos;
    private Integer duracionMinima;

    public static TransitoView toView(Transito transito){
        TransitoView view = new TransitoView();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //Animal
        view.animal = AnimalView.toView(transito.getAnimal());
        //Refugio
        view.idRefugio = transito.getRefugio().getId();
        view.nombreRefugio = transito.getRefugio().getNombre();
        view.fotoPerfilRefugio = transito.getRefugio().getPerfilRefugio().getFotoPerfil();
        view.direccionRefugio = transito.getRefugio().getDireccion();
        view.redesSocialesRefugio = transito.getRefugio().getRedesSociales().stream().map(RedSocialView::toView).collect(Collectors.toList());
        //Publicacion
        view.idPublicacion = transito.getId();
        view.descripcion = transito.getDescripcion();
        view.necesitaPatio = transito.getNecesitaPatio();
        view.puedeConvivirConInfantes = transito.getPuedeConvivirConInfantes();
        view.puedeConvivirConGatos = transito.getPuedeConvivirConGatos();
        view.puedeConvivirConCachorros = transito.getPuedeConvivirConCachorros();
        view.puedeConvivirConPerrosAdultos = transito.getPuedeConvivirConPerrosAdultos();
        view.requiereHogarAmplio = transito.getRequiereHogarAmplio();
        view.transporteCubierto = transito.getTransporteCubierto();
        view.esUrgente = transito.getEsUrgente();
        view.fechaPublicacion = transito.getFechaPublicacion().format(dateFormatter);
        view.estadoPublicacion = transito.getEstado().getNombre();
        //Transito
        view.gastosAlimentacionCubiertos = transito.getGastosAlimentacionCubiertos();
        view.gastosMedicosCubiertos = transito.getGastosMedicosCubiertos();
        view.duracionMinima = transito.getDuracionMinima();

        return view;
    }

    private TransitoView(){}
}

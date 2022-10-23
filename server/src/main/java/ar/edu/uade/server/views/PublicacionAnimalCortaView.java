package ar.edu.uade.server.views;

import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.model.PublicacionAnimal;
import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/*
Forma de uso:

Adopcion a = #Objeto de modelo adopcion
AdopcionCortaView av = AdopcionCortaView.toView(a)

 */

@Getter
@Setter
public class PublicacionAnimalCortaView {

    private String nombreAnimal;
    private String paisRefugio;
    private String provinciaRefugio;
    private String localidadRefugio;
    private String nombreRefugio;
    private String fotoPerfilRefugio;
    private Boolean esUrgente;
    private EstadoPublicacionAnimalEnum estadoPublicacion;
    private String imagenAnimal;
    private Long idAnimal;
    private Long idRefugio;
    private Long idPublicacion;

    public static PublicacionAnimalCortaView toView(PublicacionAnimal publicacion){
        PublicacionAnimalCortaView view = new PublicacionAnimalCortaView();
        view.nombreAnimal = publicacion.getAnimal().getNombre();
        view.localidadRefugio = publicacion.getRefugio().getDireccion().getLocalidad();
        view.provinciaRefugio = publicacion.getRefugio().getDireccion().getProvincia();
        view.paisRefugio = "Argentina";
        view.nombreRefugio = publicacion.getRefugio().getNombre();
        view.fotoPerfilRefugio = publicacion.getRefugio().getPerfilRefugio().getFotoPerfil();
        view.esUrgente = publicacion.getEsUrgente();
        view.estadoPublicacion = publicacion.getEstado();
        view.imagenAnimal = publicacion.getGaleriaImagenes().get(0);
        view.idAnimal = publicacion.getAnimal().getId();
        view.idRefugio = publicacion.getRefugio().getId();
        view.idPublicacion = publicacion.getId();
        return view;
    }

    public static List<PublicacionAnimalCortaView> toView(List<PublicacionAnimal> publicaciones){
        List<PublicacionAnimalCortaView> adopcionesView = new ArrayList<>();
        for (PublicacionAnimal pub : publicaciones){
            adopcionesView.add(toView(pub));
        }
        return adopcionesView;
    }

    private PublicacionAnimalCortaView(){}
}
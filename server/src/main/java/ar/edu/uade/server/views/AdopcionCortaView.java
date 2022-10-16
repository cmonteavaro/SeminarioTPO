package ar.edu.uade.server.views;

import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import lombok.Getter;
import lombok.Setter;

/*
Forma de uso:

Adopcion a = #Objeto de modelo adopcion
AdopcionCortaView av = AdopcionCortaView.toView(a)

 */

@Getter
@Setter
public class AdopcionCortaView {

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

    public static AdopcionCortaView toView(Adopcion adopcion){
        AdopcionCortaView view = new AdopcionCortaView();
        view.nombreAnimal = adopcion.getAnimal().getNombre();
        view.localidadRefugio = adopcion.getRefugio().getDireccion().getLocalidad();
        view.provinciaRefugio = adopcion.getRefugio().getDireccion().getProvincia();
        view.paisRefugio = "Argentina";
        view.nombreRefugio = adopcion.getRefugio().getNombre();
        view.fotoPerfilRefugio = adopcion.getRefugio().getPerfilRefugio().getFotoPerfil();
        view.esUrgente = adopcion.getEsUrgente();
        view.estadoPublicacion = adopcion.getEstado();
        view.imagenAnimal = adopcion.getGaleriaImagenes().get(0);
        view.idAnimal = adopcion.getAnimal().getId();
        view.idRefugio = adopcion.getRefugio().getId();
        view.idPublicacion = adopcion.getId();
        return view;
    }

    private AdopcionCortaView(){}
}

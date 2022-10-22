package ar.edu.uade.server.views;

import ar.edu.uade.server.model.Direccion;
import ar.edu.uade.server.model.PublicacionVoluntariado;
import ar.edu.uade.server.model.Refugio;
import ar.edu.uade.server.model.enums.TipoVoluntariadoEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class VoluntariadoCortaView {

    //Refugio
    private String nombreRefugio;
    private String fotoPerfilRefugio;
    private Direccion direccionRefugio;

    //Publicacion
    private long idPublicacion;
    private TipoVoluntariadoEnum tipoVoluntariado;
    private String titulo;

    private VoluntariadoCortaView(){}

    public static VoluntariadoCortaView toView(PublicacionVoluntariado publicacionVoluntariado){
        VoluntariadoCortaView vv = new VoluntariadoCortaView();
        //Refugio
        vv.nombreRefugio = publicacionVoluntariado.getRefugio().getNombre();
        vv.fotoPerfilRefugio = publicacionVoluntariado.getRefugio().getPerfilRefugio().getFotoPerfil();
        vv.direccionRefugio = publicacionVoluntariado.getRefugio().getDireccion();

        //Publicacion
        vv.idPublicacion = publicacionVoluntariado.getId();
        vv.tipoVoluntariado = publicacionVoluntariado.getTipo();
        vv.titulo = publicacionVoluntariado.getTitulo();

        return vv;
    }

}

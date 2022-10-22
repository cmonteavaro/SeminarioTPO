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
public class VoluntariadoView {

    //Refugio
    private Long idRefugio;
    private String nombreRefugio;
    private String fotoPerfilRefugio;
    private Direccion direccionRefugio;

    //Publicacion
    private long idPublicacion;
    private TipoVoluntariadoEnum tipoVoluntariado;
    private String titulo;
    private String descripcion;
    private LocalDate fechaPublicacion;

    private VoluntariadoView(){}

    public static VoluntariadoView toView(PublicacionVoluntariado publicacionVoluntariado){
        VoluntariadoView vv = new VoluntariadoView();
        //Refugio
        vv.idRefugio = publicacionVoluntariado.getRefugio().getId();
        vv.nombreRefugio = publicacionVoluntariado.getRefugio().getNombre();
        vv.fotoPerfilRefugio = publicacionVoluntariado.getRefugio().getPerfilRefugio().getFotoPerfil();
        vv.direccionRefugio = publicacionVoluntariado.getRefugio().getDireccion();

        //Publicacion
        vv.idPublicacion = publicacionVoluntariado.getId();
        vv.tipoVoluntariado = publicacionVoluntariado.getTipo();
        vv.titulo = publicacionVoluntariado.getTitulo();
        vv.descripcion = publicacionVoluntariado.getDescripcion();
        vv.fechaPublicacion = publicacionVoluntariado.getFechaPublicacion();

        return vv;
    }

}

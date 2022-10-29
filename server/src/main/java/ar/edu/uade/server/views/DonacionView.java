package ar.edu.uade.server.views;

import ar.edu.uade.server.model.PublicacionDonacion;
import ar.edu.uade.server.model.enums.TipoDonacionEnum;

import java.util.ArrayList;
import java.util.List;

public class DonacionView {

    private Long idPublicacion;
    private String titulo;
    private String descripcion;
    private TipoDonacionEnum tipo;
    private Long idRefugio;
    private String nombreRefugio;
    private String fotoPerfilRefugio;


    public static DonacionView toView (PublicacionDonacion donacion){
        DonacionView view = new DonacionView();
        view.idPublicacion = donacion.getId();
        view.titulo = donacion.getRefugio().getDireccion().getLocalidad();
        view.descripcion = donacion.getRefugio().getDireccion().getProvincia();
        view.tipo = donacion.getTipo();
        view.idRefugio = donacion.getRefugio().getId();
        view.nombreRefugio = donacion.getRefugio().getNombre();
        view.fotoPerfilRefugio = donacion.getRefugio().getPerfilRefugio().getFotoPerfil();
        return view;
    }

    public static List<DonacionView> toView (List<PublicacionDonacion> donaciones) {
        List<DonacionView> donacionesView = new ArrayList<>();
        for (PublicacionDonacion donacion : donaciones) {
            donacionesView.add(toView(donacion));
        }
        return donacionesView;
    }
}
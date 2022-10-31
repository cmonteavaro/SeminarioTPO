package ar.edu.uade.server.views;

import ar.edu.uade.server.model.Direccion;
import ar.edu.uade.server.model.PublicacionDonacion;
import ar.edu.uade.server.model.enums.TipoDonacionEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DonacionView {

    private Long idPublicacion;
    private String titulo;
    private String descripcion;
    private TipoDonacionEnum tipo;
    private String fechaPublicacion;
    private Long idRefugio;
    private String nombreRefugio;
    private String fotoPerfilRefugio;
    private Direccion direccionRefugio;


    public static DonacionView toView (PublicacionDonacion donacion){
        DonacionView view = new DonacionView();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        view.idPublicacion = donacion.getId();
        view.titulo = donacion.getTitulo();
        view.descripcion = donacion.getDescripcion();
        view.tipo = donacion.getTipo();
        view.fechaPublicacion = donacion.getFechaPublicacion().format(dateFormatter);
        view.idRefugio = donacion.getRefugio().getId();
        view.nombreRefugio = donacion.getRefugio().getNombre();
        view.fotoPerfilRefugio = donacion.getRefugio().getPerfilRefugio().getFotoPerfil();
        view.direccionRefugio = donacion.getRefugio().getDireccion();
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
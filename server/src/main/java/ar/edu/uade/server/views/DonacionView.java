package ar.edu.uade.server.views;

import ar.edu.uade.server.model.PublicacionAnimal;
import ar.edu.uade.server.model.PublicacionDonacion;
import ar.edu.uade.server.model.enums.TipoDonacionEnum;

import java.util.ArrayList;
import java.util.List;

public class DonacionView {

        private String titulo;
        private String descripcion;
        private TipoDonacionEnum tipo;
        private String localidadRefugio;
        private String nombreRefugio;


        public static DonacionView toView (PublicacionDonacion donacion){
            DonacionView view = new DonacionView();
            view.
                    view.nombreAnimal = publicacion.getAnimal().getNombre();
            view.localidadRefugio = publicacion.getRefugio().getDireccion().getLocalidad();
            view.provinciaRefugio = publicacion.getRefugio().getDireccion().getProvincia();
            view.paisRefugio = "Argentina";
            view.nombreRefugio = publicacion.getRefugio().getNombre();
            view.fotoPerfilRefugio = publicacion.getRefugio().getPerfilRefugio().getFotoPerfil();
            view.esUrgente = publicacion.getEsUrgente();
            view.estadoPublicacion = publicacion.getEstado();
            view.imagenAnimal = publicacion.getAnimal().getGaleriaImagenes().get(0);
            view.idAnimal = publicacion.getAnimal().getId();
            view.idRefugio = publicacion.getRefugio().getId();
            view.idPublicacion = publicacion.getId();
            return view;
        }

        public static List<PublicacionAnimalCortaView> toView (List < PublicacionAnimal > publicaciones) {
            List<PublicacionAnimalCortaView> adopcionesView = new ArrayList<>();
            for (PublicacionAnimal pub : publicaciones) {
                adopcionesView.add(toView(pub));
            }
            return adopcionesView;
        }

    }
}
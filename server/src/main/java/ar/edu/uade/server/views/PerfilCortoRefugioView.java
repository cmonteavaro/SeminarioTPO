package ar.edu.uade.server.views;

import ar.edu.uade.server.model.RedSocial;
import ar.edu.uade.server.model.Refugio;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PerfilCortoRefugioView {

    private long id;
    private String nombre;
    private List<RedSocial> redesSociales;
    private String fotoPerfil;
    private String descripcionCorta;

    public static PerfilCortoRefugioView toView(Refugio ref){
        PerfilCortoRefugioView view = new PerfilCortoRefugioView();
        view.id = ref.getId();
        view.nombre = ref.getNombre();
        view.redesSociales = ref.getRedesSociales();
        view.fotoPerfil = ref.getPerfilRefugio().getFotoPerfil();
        view.descripcionCorta = ref.getPerfilRefugio().getDescripcionCorta();
        return view;
    }

    private PerfilCortoRefugioView(){}
}

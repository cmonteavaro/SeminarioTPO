package ar.edu.uade.server.views;

import ar.edu.uade.server.model.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PerfilRefugioView {

    private long id;
    private String nombre;
    private Direccion direccion;
    private String telefono;
    private String linkDonacionesMonetarias;
    private Integer radioAlcance;
    private List<RedSocialView> redesSociales;
    private PerfilRefugio perfilRefugio;

    public static PerfilRefugioView toView(Refugio ref){
        PerfilRefugioView view = new PerfilRefugioView();
        view.id = ref.getId();
        view.nombre = ref.getNombre();
        view.direccion = ref.getDireccion();
        view.telefono = ref.getTelefono();
        view.linkDonacionesMonetarias = ref.getLinkDonacionesMonetarias();
        view.radioAlcance = ref.getRadioAlcance();
        List<RedSocialView> redesSociales = new ArrayList<>();
        ref.getRedesSociales().forEach(red -> redesSociales.add(RedSocialView.toView(red)));
        view.redesSociales = redesSociales;
        view.perfilRefugio = ref.getPerfilRefugio();
        return view;
    }

    private PerfilRefugioView(){}
}

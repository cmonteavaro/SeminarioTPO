package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import java.awt.*;
import java.util.*;
import java.util.List;

@Getter
@Setter
@Embeddable
public class PerfilRefugio {

    private String fotoPerfil;
    private String banner;
    private String descripcionCorta;
    private String descripcionLarga;
    private String color;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> galeriaImagenes;

    public PerfilRefugio() {
        this.galeriaImagenes = new ArrayList<String>();
    }

    public void agregarImagenes(String ... imagenes) { Collections.addAll(galeriaImagenes, imagenes); }

    public void eliminarImagen(String imagen) { if(this.galeriaImagenes.contains(imagen)) this.galeriaImagenes.remove(imagen); }

}

package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import java.awt.*;
import java.util.*;
import java.util.List;

@Getter
@Setter
@Embeddable
public class PerfilRefugio {

    @Setter
    private String fotoPerfil;
    @Setter
    private String banner;
    @Setter
    private String descripcionCorta;
    @Setter
    private String descripcionLarga;
    @Setter
    @Enumerated
    private Color color;

    @ElementCollection
    private List<String> galeriaImagenes;

    public PerfilRefugio() {
        this.galeriaImagenes = new ArrayList<String>();
    }

    public void agregarImagenes(String ... imagenes) { Collections.addAll(galeriaImagenes, imagenes); }

    public void eliminarImagen(String imagen) { if(this.galeriaImagenes.contains(imagen)) this.galeriaImagenes.remove(imagen); }

}
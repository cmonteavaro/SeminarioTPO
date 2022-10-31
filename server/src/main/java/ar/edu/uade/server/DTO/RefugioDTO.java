package ar.edu.uade.server.DTO;

import ar.edu.uade.server.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RefugioDTO {

    private String nombre;
    private Direccion direccion;
    private PerfilRefugio perfilRefugio;
    private String usuario;
    private String password;
    private String responsable;
    private String correo;
    private String telefono;
    private String linkDonacionesMonetarias;
    private Integer radioAlcance;
    private List<RedSocialDTO> redesSociales;
    private List<Adopcion> publicacionesAdopcion;
    private List<Transito> publicacionesTransito;
    private List<PublicacionVoluntariado> publicacionesVoluntariado;
    private List<PublicacionDonacion> publicacionesDonacionesNoMonetarias;

    public Refugio toModel() {
        Refugio refugio = new Refugio();

        refugio.setNombre(this.nombre);
        refugio.setDireccion(this.direccion);
        refugio.setPerfilRefugio(this.perfilRefugio);
        refugio.setUsuario(this.usuario);
        refugio.setPassword(this.password);
        refugio.setResponsable(this.responsable);
        refugio.setCorreo(this.correo);
        refugio.setTelefono(this.telefono);
        refugio.setLinkDonacionesMonetarias(this.linkDonacionesMonetarias);
        refugio.setRadioAlcance(this.radioAlcance);
        refugio.setRedesSociales(this.redesSociales.stream().map(RedSocialDTO::toModel).toList());

        return refugio;
    }
}

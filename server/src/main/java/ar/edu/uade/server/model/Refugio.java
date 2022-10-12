package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
public class Refugio {

    @Setter
    private String nombre;
    @Setter
    private Direccion direccion;
    @Setter
    private String usuario;
    @Setter
    private String password;
    @Setter
    private String responsable;
    @Setter
    private Integer telefono;
    @Setter
    private String linkDonacionesMonetarias;
    @Setter
    private Integer radioAlcance;

    private List<RedSocial> redesSociales;
    private List<PublicacionAnimal> publicacionesAnimales;
    private List<PublicacionVoluntariado> publicacionesVoluntariado;
    private List<PublicacionDonacion> publicacionesDonacionesNoMonetarias;


    public Refugio(String nombre, String usuario, String password) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.redesSociales = new ArrayList<RedSocial>();
        this.publicacionesAnimales = new ArrayList<PublicacionAnimal>();
        this.publicacionesVoluntariado = new ArrayList<PublicacionVoluntariado>();
        this.publicacionesDonacionesNoMonetarias = new ArrayList<PublicacionDonacion>();
    }

    public void agregarRedesSociales(RedSocial ... redes) { Collections.addAll(redesSociales, redes); }

    public void eliminarRedSocial(RedSocial red) { if(this.redesSociales.contains(red)) this.redesSociales.remove(red); }

    public void agregarPublicacionAnimal(PublicacionAnimal publicacion) { this.publicacionesAnimales.add(publicacion); }

    public void eliminarPublicacionAnimal(PublicacionAnimal publicacion) { if(this.publicacionesAnimales.contains(publicacion)) this.publicacionesAnimales.remove(publicacion); }

    public void agregarPublicacionVoluntariado(PublicacionVoluntariado publicacion) { this.publicacionesVoluntariado.add(publicacion); }

    public void eliminarPublicacionVoluntariado(PublicacionVoluntariado publicacion) { if(this.publicacionesVoluntariado.contains(publicacion)) this.publicacionesVoluntariado.remove(publicacion); }

    public void agregarPublicacionDonacion(PublicacionDonacion publicacion) { this.publicacionesDonacionesNoMonetarias.add(publicacion); }

    public void eliminarPublicacionDonacion(PublicacionDonacion publicacion) { if(this.publicacionesDonacionesNoMonetarias.contains(publicacion)) this.publicacionesDonacionesNoMonetarias.remove(publicacion); }

}
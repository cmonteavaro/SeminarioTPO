package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import javax.jdo.annotations.EmbeddedOnly;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
public class Refugio {

    @Id
    @GeneratedValue
    private long id;
    private String nombre;
    @Embedded
    private Direccion direccion;

    @Embedded
    private PerfilRefugio perfilRefugio;
    private String usuario;
    private String password;
    private String responsable;
    private Integer telefono;
    private String linkDonacionesMonetarias;
    private Integer radioAlcance;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<RedSocial> redesSociales;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Adopcion> publicacionesAdopcion;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Transito> publicacionesTransito;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<PublicacionVoluntariado> publicacionesVoluntariado;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<PublicacionDonacion> publicacionesDonacionesNoMonetarias;


    public Refugio(String nombre, String usuario, String password) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.password = password;
        this.redesSociales = new ArrayList<RedSocial>();
        this.publicacionesAdopcion = new ArrayList<Adopcion>();
        this.publicacionesTransito = new ArrayList<Transito>();
        this.publicacionesVoluntariado = new ArrayList<PublicacionVoluntariado>();
        this.publicacionesDonacionesNoMonetarias = new ArrayList<PublicacionDonacion>();
    }

    public Refugio() {
        this.redesSociales = new ArrayList<RedSocial>();
        this.publicacionesAdopcion = new ArrayList<Adopcion>();
        this.publicacionesTransito = new ArrayList<Transito>();
        this.publicacionesVoluntariado = new ArrayList<PublicacionVoluntariado>();
        this.publicacionesDonacionesNoMonetarias = new ArrayList<PublicacionDonacion>();
    }

    public void agregarRedesSociales(RedSocial ... redes) { Collections.addAll(redesSociales, redes); }

    public void eliminarRedSocial(RedSocial red) { if(this.redesSociales.contains(red)) this.redesSociales.remove(red); }

    public void agregarPublicacionAdopcion(Adopcion publicacion) { this.publicacionesAdopcion.add(publicacion); }
    public void agregarPublicacionTransito(Transito publicacion) { this.publicacionesTransito.add(publicacion); }

    public void eliminarPublicacionAdopcion(Adopcion publicacion) { if(this.publicacionesAdopcion.contains(publicacion)) this.publicacionesAdopcion.remove(publicacion); }
    public void eliminarPublicacionTransito(Transito publicacion) { if(this.publicacionesTransito.contains(publicacion)) this.publicacionesTransito.remove(publicacion); }

    public void agregarPublicacionVoluntariado(PublicacionVoluntariado publicacion) { this.publicacionesVoluntariado.add(publicacion); }

    public void eliminarPublicacionVoluntariado(PublicacionVoluntariado publicacion) { if(this.publicacionesVoluntariado.contains(publicacion)) this.publicacionesVoluntariado.remove(publicacion); }

    public void agregarPublicacionDonacion(PublicacionDonacion publicacion) { this.publicacionesDonacionesNoMonetarias.add(publicacion); }

    public void eliminarPublicacionDonacion(PublicacionDonacion publicacion) { if(this.publicacionesDonacionesNoMonetarias.contains(publicacion)) this.publicacionesDonacionesNoMonetarias.remove(publicacion); }

}
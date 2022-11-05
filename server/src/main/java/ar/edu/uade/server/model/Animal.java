package ar.edu.uade.server.model;
import ar.edu.uade.server.model.enums.TamanioEnum;
import ar.edu.uade.server.model.enums.TipoAnimalEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Getter
@Setter
@Entity
public class Animal {
    @Id
    @GeneratedValue
    private long id;
    private String nombre;
    @Enumerated
    private TamanioEnum tamanioActual;
    @Enumerated
    private TamanioEnum tamanioEsperado;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDate fechaNac;
    @Enumerated
    private TipoAnimalEnum tipoAnimal;
    private Boolean castrado;
    private Boolean esquemaCompletoVacunas;
    private Boolean desparasitado;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> galeriaImagenes;
    public Animal(String nombre, TipoAnimalEnum tipoAnimal) {
        this.nombre = nombre;
        this.tipoAnimal = tipoAnimal;
        this.galeriaImagenes = new ArrayList<String>();
    }

    public Animal() {
        this.galeriaImagenes = new ArrayList<String>();
    }

    public String getEdad() {
        String edad;
        LocalDate fechaActual = LocalDate.now();
        Period diferenciaFechaNacAHoy = Period.between(this.fechaNac, fechaActual);
        int diferenciaEnAnios = diferenciaFechaNacAHoy.getYears();
        int diferenciaEnMeses = diferenciaFechaNacAHoy.getMonths();
        if (diferenciaEnAnios == 0 && diferenciaEnMeses < 2) {
            edad = diferenciaFechaNacAHoy.getDays() + " días";
        }
        else if (diferenciaEnAnios < 1) {
            edad = diferenciaEnMeses + " meses";
        }
        else {
            if (this.fechaNac.getDayOfMonth() == fechaActual.getDayOfMonth() && this.fechaNac.getMonthValue() == fechaActual.getMonthValue()) {
                edad = diferenciaEnAnios + (diferenciaEnAnios == 1 ? " año" : " años") + " (hoy " + this.getNombre() + " está cumpliendo años)";
            }
            else {
                edad = diferenciaEnAnios + (diferenciaEnAnios == 1 ? " año" : " años");
            }
        }

        return edad;
    }

    public Integer getEdadInteger() {
        return Period.between(this.fechaNac, LocalDate.now()).getYears();
    }

    public void agregarImagenes(String ... imagenes) { Collections.addAll(galeriaImagenes, imagenes); }

    public void eliminarImagen(String imagen) { if(this.galeriaImagenes.contains(imagen)) this.galeriaImagenes.remove(imagen); }

}
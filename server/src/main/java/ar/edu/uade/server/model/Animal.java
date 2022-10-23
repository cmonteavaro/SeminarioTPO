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
    @Temporal(TemporalType.DATE)
    private LocalDate fechaNac;
    @Enumerated
    private TipoAnimalEnum tipoAnimal;
    private Boolean castrado;
    private Boolean esquemaCompletoVacunas;
    private Boolean desparasitado;

    public Animal(String nombre, TipoAnimalEnum tipoAnimal) {
        this.nombre = nombre;
        this.tipoAnimal = tipoAnimal;
    }

    public Animal() {

    }

    public Integer getEdad() {
        return Period.between(this.fechaNac, LocalDate.now()).getYears();
    }

}
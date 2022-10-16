package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Transito extends PublicacionAnimal {

    private Boolean gastosAlimentacionCubiertos;
    private Boolean gastosMedicosCubiertos;
    private Integer duracionMinima;

    public Transito() {
        super();
    }

}
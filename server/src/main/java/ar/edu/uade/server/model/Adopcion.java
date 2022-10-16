package ar.edu.uade.server.model;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

@Getter
@Setter
@Entity
public class Adopcion extends PublicacionAnimal {

    public Adopcion() {
        super();
    }
}
package ar.edu.uade.server.DTO;

import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.model.enums.TamanioEnum;
import ar.edu.uade.server.model.enums.TipoAnimalEnum;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class AnimalDTO {

    private String nombre;
    private String tamanioActual;
    private String tamanioEsperado;
    private String fechaNac;
    private String tipoAnimal;
    private Boolean castrado;
    private Boolean esquemaCompletoVacunas;
    private Boolean desparasitado;

    public Animal toModel() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Animal animal = new Animal();
        animal.setNombre(this.nombre);
        animal.setTamanioActual(TamanioEnum.getEnum(this.tamanioActual));
        animal.setTamanioEsperado(TamanioEnum.getEnum(this.tamanioEsperado));
        animal.setFechaNac(LocalDate.parse(this.fechaNac, dateFormatter));
        animal.setTipoAnimal(TipoAnimalEnum.getEnum(this.tipoAnimal));
        animal.setCastrado(this.castrado);
        animal.setEsquemaCompletoVacunas(this.esquemaCompletoVacunas);
        animal.setDesparasitado(this.desparasitado);

        return animal;
    }
}

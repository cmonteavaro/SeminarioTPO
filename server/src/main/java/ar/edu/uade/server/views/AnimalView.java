package ar.edu.uade.server.views;

import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.model.enums.TamanioEnum;
import ar.edu.uade.server.model.enums.TipoAnimalEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AnimalView {

    private long id;
    private String nombre;
    private TamanioEnum tamanioActual;
    private TamanioEnum tamanioEsperado;
    private Integer edad;
    private TipoAnimalEnum tipoAnimal;
    private Boolean castrado;
    private Boolean esquemaCompletoVacunas;
    private Boolean desparasitado;
    private List<String> galeriaImagenes;

    public static AnimalView toView(Animal animal) {
        AnimalView view = new AnimalView();

        view.id = animal.getId();
        view.nombre = animal.getNombre();
        view.tamanioActual = animal.getTamanioActual();
        view.tamanioEsperado = animal.getTamanioEsperado();
        view.edad = animal.getEdad();
        view.tipoAnimal = animal.getTipoAnimal();
        view.castrado = animal.getCastrado();
        view.esquemaCompletoVacunas = animal.getEsquemaCompletoVacunas();
        view.desparasitado = animal.getDesparasitado();
        view.galeriaImagenes = animal.getGaleriaImagenes();

        return view;
    }

    public static List<AnimalView> toView(List<Animal> animales) {
        List<AnimalView> views = new ArrayList<>();
        animales.forEach(animal -> views.add(AnimalView.toView(animal)));
        return views;
    }

    private AnimalView() {}
}

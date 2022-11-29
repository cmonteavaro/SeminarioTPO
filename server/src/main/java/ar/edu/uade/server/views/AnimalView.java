package ar.edu.uade.server.views;

import ar.edu.uade.server.model.Animal;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AnimalView {

    private long id;
    private String nombre;
    private String tamanioActual;
    private String tamanioEsperado;
    private String edad;
    private String tipoAnimal;
    private Integer edadInteger;
    private Boolean castrado;
    private Boolean esquemaCompletoVacunas;
    private Boolean desparasitado;
    private String imagenPrincipal;
    private List<String> galeriaImagenes;

    public static AnimalView toView(Animal animal) {
        AnimalView view = new AnimalView();

        view.id = animal.getId();
        view.nombre = animal.getNombre();
        view.tamanioActual = animal.getTamanioActual().getNombre();
        view.tamanioEsperado = animal.getTamanioEsperado().getNombre();
        view.edad = animal.getEdad();
        view.edadInteger = animal.getEdadInteger();
        view.tipoAnimal = animal.getTipoAnimal().getNombre();
        view.castrado = animal.getCastrado();
        view.esquemaCompletoVacunas = animal.getEsquemaCompletoVacunas();
        view.desparasitado = animal.getDesparasitado();
        view.imagenPrincipal = animal.getGaleriaImagenes().size() != 0 ? animal.getGaleriaImagenes().get(0) : null;
        view.galeriaImagenes = animal.getGaleriaImagenes().size() > 1 ? animal.getGaleriaImagenes().subList(1, animal.getGaleriaImagenes().size()) : null;


        return view;
    }

    public static List<AnimalView> toView(List<Animal> animales) {
        List<AnimalView> views = new ArrayList<>();
        animales.forEach(animal -> views.add(AnimalView.toView(animal)));
        return views;
    }

    private AnimalView() {}
}

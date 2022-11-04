package ar.edu.uade.server.views;


import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import ar.edu.uade.server.model.enums.TamanioEnum;
import ar.edu.uade.server.model.enums.TipoAnimalEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AtributosView {

    private TipoAnimalEnum[] tipoAnimal;
    private List<String> tamanioAnimal;
    private List<String> estadoPublicacion;

    public static AtributosView toView(){
        AtributosView av = new AtributosView();

        av.tipoAnimal = TipoAnimalEnum.values();
        av.tamanioAnimal = Arrays.stream(TamanioEnum.values()).map(tamanio -> tamanio.getNombre()).collect(Collectors.toList());
        av.estadoPublicacion = Arrays.stream(EstadoPublicacionAnimalEnum.values()).map(estado -> estado.getNombre()).collect(Collectors.toList());
        av.estadoPublicacion.remove(EstadoPublicacionAnimalEnum.FINALIZADA.getNombre());

        return av;
    }

    private AtributosView(){}
}

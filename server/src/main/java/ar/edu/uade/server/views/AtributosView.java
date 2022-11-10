package ar.edu.uade.server.views;


import ar.edu.uade.server.DTO.TransitoDTO;
import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.model.PublicacionAnimal;
import ar.edu.uade.server.model.Transito;
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

    private List<String> booleanos;
    private List<String> tipoAnimal;
    private List<String> tamanioAnimal;
    private List<String> estadoPublicacion;

    public static AtributosView toView(){
        AtributosView av = new AtributosView();

        av.booleanos = Arrays.stream(Transito.class.getDeclaredFields()).filter(attr -> attr.getType().equals(Boolean.class)).map(attr -> attr.getName().replaceAll("(?=[A-Z]+)", " ").trim().replaceFirst(attr.getName().substring(0,1),attr.getName().substring(0,1).toUpperCase())).collect(Collectors.toList());
        av.booleanos.addAll(Arrays.stream(PublicacionAnimal.class.getDeclaredFields()).filter(attr -> attr.getType().equals(Boolean.class)).map(attr -> attr.getName().replaceAll("(?=[A-Z]+)", " ").trim().replaceFirst(attr.getName().substring(0,1),attr.getName().substring(0,1).toUpperCase())).collect(Collectors.toList()));
        av.booleanos.addAll(Arrays.stream(Animal.class.getDeclaredFields()).filter(attr -> attr.getType().equals(Boolean.class)).map(attr -> attr.getName().replaceAll("(?=[A-Z]+)", " ").trim().replaceFirst(attr.getName().substring(0,1),attr.getName().substring(0,1).toUpperCase())).collect(Collectors.toList()));

        av.tipoAnimal = Arrays.stream(TipoAnimalEnum.values()).map(tipo -> tipo.getNombre()).collect(Collectors.toList());
        av.tamanioAnimal = Arrays.stream(TamanioEnum.values()).map(tamanio -> tamanio.getNombre()).collect(Collectors.toList());
        av.estadoPublicacion = Arrays.stream(EstadoPublicacionAnimalEnum.values()).map(estado -> estado.getNombre()).collect(Collectors.toList());
        av.estadoPublicacion.remove(EstadoPublicacionAnimalEnum.FINALIZADA.getNombre());

        return av;
    }

    private AtributosView(){}
}

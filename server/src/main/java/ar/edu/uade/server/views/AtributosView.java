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

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class AtributosView {

    private List<String> booleanos;
    private Map<String,List<String>> multivalores;

    public static AtributosView toView(){
        AtributosView av = new AtributosView();
        av.multivalores = new HashMap<>();

        av.booleanos = Arrays.stream(PublicacionAnimal.class.getDeclaredFields()).filter(attr -> attr.getType().equals(Boolean.class)).map(attr -> attr.getName().replaceAll("(?=[A-Z]+)", " ").trim().toLowerCase().replaceFirst(attr.getName().substring(0,1),attr.getName().substring(0,1).toUpperCase())).collect(Collectors.toList());
        av.booleanos.remove("Es urgente");
        av.booleanos.remove("Transporte cubierto");
        av.booleanos.add("Castrado");

        av.multivalores.put(TipoAnimalEnum.class.getSimpleName(),Arrays.stream(TipoAnimalEnum.values()).map(tipo -> tipo.getNombre()).collect(Collectors.toList()));
        av.multivalores.put(TamanioEnum.class.getSimpleName(),Arrays.stream(TamanioEnum.values()).map(tamanio -> tamanio.getNombre()).collect(Collectors.toList()));
        av.multivalores.put(EstadoPublicacionAnimalEnum.class.getSimpleName(),Arrays.stream(EstadoPublicacionAnimalEnum.values()).map(estado -> estado.getNombre()).collect(Collectors.toList()));
        av.multivalores.get(EstadoPublicacionAnimalEnum.class.getSimpleName()).remove(EstadoPublicacionAnimalEnum.FINALIZADA.getNombre());

        return av;
    }

    private AtributosView(){}
}

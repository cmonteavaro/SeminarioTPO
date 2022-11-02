package ar.edu.uade.server.DTO;

import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.model.Refugio;
import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import ar.edu.uade.server.service.AdopcionService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class AdopcionDTO {

    private Long idPublicacion;
    private String descripcion;
    private Long idAnimal;
    private Long idRefugio;
    private Boolean necesitaPatio;
    private Boolean puedeConvivirConInfantes;
    private Boolean puedeConvivirConGatos;
    private Boolean puedeConvivirConCachorros;
    private Boolean puedeConvivirConPerrosAdultos;
    private Boolean requiereHogarAmplio;
    private Boolean transporteCubierto;
    private Boolean esUrgente;
    private String fechaPublicacion;
    private String estado;

    public Adopcion toModel() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Adopcion adopcion = new Adopcion();
        adopcion.setId(this.idPublicacion);
        adopcion.setDescripcion(this.descripcion);
        adopcion.setNecesitaPatio(this.necesitaPatio);
        adopcion.setPuedeConvivirConInfantes(this.puedeConvivirConInfantes);
        adopcion.setPuedeConvivirConGatos(this.puedeConvivirConGatos);
        adopcion.setPuedeConvivirConCachorros(this.puedeConvivirConCachorros);
        adopcion.setPuedeConvivirConPerrosAdultos(this.puedeConvivirConPerrosAdultos);
        adopcion.setRequiereHogarAmplio(this.requiereHogarAmplio);
        adopcion.setTransporteCubierto(this.transporteCubierto);
        adopcion.setEsUrgente(this.esUrgente);
        adopcion.setFechaPublicacion(LocalDate.parse(this.fechaPublicacion, dateFormatter));
        adopcion.setEstado(EstadoPublicacionAnimalEnum.getEnum(this.estado));
        return adopcion;
    }
}

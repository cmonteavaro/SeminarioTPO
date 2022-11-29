package ar.edu.uade.server.DTO;

import ar.edu.uade.server.model.Transito;
import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
public class TransitoDTO {

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
    private Boolean gastosAlimentacionCubiertos;
    private Boolean gastosMedicosCubiertos;
    private Integer duracionMinima;
    private Boolean esUrgente;
    private String fechaPublicacion;
    private String estado;

    public Transito toModel() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Transito transito = new Transito();

        transito.setId(this.idPublicacion);
        transito.setDescripcion(this.descripcion);
        transito.setNecesitaPatio(this.necesitaPatio);
        transito.setPuedeConvivirConInfantes(this.puedeConvivirConInfantes);
        transito.setPuedeConvivirConGatos(this.puedeConvivirConGatos);
        transito.setPuedeConvivirConCachorros(this.puedeConvivirConCachorros);
        transito.setPuedeConvivirConPerrosAdultos(this.puedeConvivirConPerrosAdultos);
        transito.setRequiereHogarAmplio(this.requiereHogarAmplio);
        transito.setTransporteCubierto(this.transporteCubierto);
        transito.setEsUrgente(this.esUrgente);
        transito.setFechaPublicacion(LocalDate.parse(this.fechaPublicacion, dateFormatter));
        transito.setEstado(EstadoPublicacionAnimalEnum.getEnum(this.estado));
        transito.setGastosAlimentacionCubiertos(this.gastosAlimentacionCubiertos);
        transito.setGastosMedicosCubiertos(this.gastosMedicosCubiertos);
        transito.setDuracionMinima(this.duracionMinima);

        return transito;
    }
}

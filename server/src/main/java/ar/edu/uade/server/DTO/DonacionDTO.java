package ar.edu.uade.server.DTO;

import ar.edu.uade.server.model.PublicacionDonacion;
import ar.edu.uade.server.model.enums.TipoDonacionEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class DonacionDTO {
    private Long idPublicacion;
    private String titulo;
    private String descripcion;
    private TipoDonacionEnum tipo;
    private Long idRefugio;
    private String fechaPublicacion;

    public PublicacionDonacion toModel() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        PublicacionDonacion donacion = new PublicacionDonacion();
        donacion.setId(this.idPublicacion);
        donacion.setTitulo(this.titulo);
        donacion.setDescripcion(this.descripcion);
        donacion.setTipo(this.tipo);
        donacion.setFechaPublicacion(LocalDate.parse(this.fechaPublicacion, dateFormatter));

        return donacion;
    }
}


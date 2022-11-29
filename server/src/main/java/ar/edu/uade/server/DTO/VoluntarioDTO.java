package ar.edu.uade.server.DTO;

import ar.edu.uade.server.model.PublicacionVoluntariado;
import ar.edu.uade.server.model.Refugio;
import ar.edu.uade.server.model.enums.TipoVoluntariadoEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class VoluntarioDTO {

    private Long idPublicacion;
    private TipoVoluntariadoEnum tipoVoluntariado;
    private String titulo;
    private String descripcion;
    private Long idRefugio;
    private String fechaPublicacion;

    public PublicacionVoluntariado toModel(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        PublicacionVoluntariado publicacionVoluntariado = new PublicacionVoluntariado();
        publicacionVoluntariado.setId(this.idPublicacion);
        publicacionVoluntariado.setTipo(this.tipoVoluntariado);
        publicacionVoluntariado.setTitulo(this.titulo);
        publicacionVoluntariado.setDescripcion(this.descripcion);
        publicacionVoluntariado.setFechaPublicacion(LocalDate.parse(this.fechaPublicacion,dateTimeFormatter));

        return publicacionVoluntariado;
    }
}

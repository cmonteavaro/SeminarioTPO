package ar.edu.uade.server.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FormularioDTO {
    String nombre;
    String apellido;
    String telefono;
    String direccion;
    String correo;
    String notas;
}

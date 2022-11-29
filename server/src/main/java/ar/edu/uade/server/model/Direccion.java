package ar.edu.uade.server.model;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.IOException;
import java.util.*;

@Getter
@Setter
@Embeddable
public class Direccion {

    private String calle;
    private Integer altura;
    private String localidad;
    private String codigoPostal;
    private String provincia;
    private Float latitud;
    private Float longitud;

    public Direccion() {

    }

    public void convertirDireccion() throws IOException, InterruptedException {

        Geocoder geocoder = new Geocoder();
        ObjectMapper mapper = new ObjectMapper();
        String locacion = this.getAltura().toString()+" "+this.getCalle()+", "+this.getLocalidad()+", "+this.getProvincia()+", "+this.getCodigoPostal();
        String response = geocoder.GeocodeSync(locacion);
        JsonNode responseJsonNode = mapper.readTree(response);

        JsonNode items = responseJsonNode.get("features");
        JsonNode item1 = items.get(0);
        JsonNode properties = item1.get("properties");

        this.setLatitud(properties.get("lat").floatValue());
        this.setLongitud(properties.get("lon").floatValue());

    }
}
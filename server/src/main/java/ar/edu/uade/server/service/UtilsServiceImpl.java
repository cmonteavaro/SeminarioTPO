package ar.edu.uade.server.service;

import ar.edu.uade.server.model.Geocoder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UtilsServiceImpl {

    public double distanciaCoords(float latitudColab, float longitudColab, float latitudRef, float longitudRef) {
        double radioTierra = 6371;

        double dLat = Math.toRadians(latitudRef-latitudColab);
        double dLong = Math.toRadians(longitudRef-longitudColab);

        double sindLat = Math.sin(dLat / 2);
        double sindLong = Math.sin(dLong / 2);

        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLong, 2) * Math.cos(Math.toRadians(latitudColab)) * Math.cos(Math.toRadians(latitudRef));
        double va2 = 2 * Math.atan2(Math.sqrt(va1),Math.sqrt(1-va1));

        double distancia = radioTierra * va2;

        return distancia;
    }

    public List<Float> convertirDireccion (String locacion) throws IOException, InterruptedException {
        List<Float> coordenadas = new ArrayList<>();

        Geocoder geocoder = new Geocoder();
        ObjectMapper mapper = new ObjectMapper();
        String response = geocoder.GeocodeSync(locacion);
        JsonNode responseJsonNode = mapper.readTree(response);

        JsonNode items = responseJsonNode.get("features");
        JsonNode item1 = items.get(0);
        JsonNode properties = item1.get("properties");

        coordenadas.add(0, properties.get("lat").floatValue());
        coordenadas.add(1, properties.get("lon").floatValue());

        return coordenadas;
    }
}

package ar.edu.uade.server.apiControllers;

import ar.edu.uade.server.DTO.AdopcionDTO;
import ar.edu.uade.server.DTO.FormularioDTO;
import ar.edu.uade.server.DTO.DonacionDTO;
import ar.edu.uade.server.DTO.TransitoDTO;
import ar.edu.uade.server.DTO.VoluntarioDTO;
import ar.edu.uade.server.exceptions.RefugioException;
import ar.edu.uade.server.model.*;
import ar.edu.uade.server.service.RefugioService;
import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import ar.edu.uade.server.service.DonacionService;
import ar.edu.uade.server.service.TransitoService;
import ar.edu.uade.server.views.*;
import ar.edu.uade.server.service.AdopcionService;
import ar.edu.uade.server.service.EmailServiceImpl;
import ar.edu.uade.server.service.VoluntarioService;
import ar.edu.uade.server.views.AdopcionView;
import ar.edu.uade.server.views.VoluntariadoView;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.Math;

@CrossOrigin
@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionesApi {

    private final AdopcionService adopcionService;
    private final TransitoService transitoService;
    private final VoluntarioService voluntarioService;
    private final EmailServiceImpl emailService;
    private final RefugioService refugioService;
    private final DonacionService donacionService;

    @Autowired
    public PublicacionesApi (VoluntarioService vs, AdopcionService as, TransitoService ts, RefugioService rs, DonacionService ds, EmailServiceImpl es){
            this.adopcionService = as;
            this.voluntarioService = vs;
            this.emailService = es;
            this.transitoService = ts;
            this.refugioService = rs;
            this.donacionService = ds;
    }

    @GetMapping("/distance/{idRefugio}")
    public ResponseEntity<?> getDistance (@RequestBody String direccion, @PathVariable Long idRefugio) {
        boolean puedeConcretar = false;
        try {
            List<Float> coords = convertirDireccion(direccion);
            Optional<Refugio> oRefugio = refugioService.findById(idRefugio);
            if (oRefugio.isPresent()){
                Refugio r = oRefugio.get();
                double distancia = distanciaCoords(coords.get(0),coords.get(1), r.getDireccion().getLatitud(),r.getDireccion().getLongitud());
                System.out.println(distancia);
                if(distancia < r.getRadioAlcance()) {
                    puedeConcretar = true;
                }
            }
        } catch (IOException | InterruptedException E) {
            return ResponseEntity.badRequest().body(E.getMessage());
        }
        return ResponseEntity.ok(puedeConcretar);
    }

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
        System.out.println(response);
        JsonNode responseJsonNode = mapper.readTree(response);

        JsonNode items = responseJsonNode.get("features");
        JsonNode item1 = items.get(0);
        JsonNode properties = item1.get("properties");

        coordenadas.add(0, properties.get("lat").floatValue());
        coordenadas.add(1, properties.get("lon").floatValue());

        System.out.println("LATITUD Colab: " + coordenadas.get(0));
        System.out.println("LONGITUD Colab: " + coordenadas.get(1));

        return coordenadas;
    }

    @GetMapping("/filtros")
    public ResponseEntity<?> getAllAtributos () {
        return ResponseEntity.ok(AtributosView.toView());
    }

    @GetMapping("/adopciones")
    public ResponseEntity<?> getAllAdopciones() {
        List<PublicacionAnimalCortaView> resultado = new ArrayList<>();
        adopcionService.findAll().stream().filter(adopcion -> !adopcion.getEstado().equals(EstadoPublicacionAnimalEnum.FINALIZADA)).forEach(adopcion -> resultado.add(PublicacionAnimalCortaView.toView(adopcion)));
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/adopciones/{id}")
    public ResponseEntity<?> getAdopcionById(@PathVariable Long id) {
        Optional<Adopcion> oAdopcion = adopcionService.findById(id);
        if (oAdopcion.isPresent()) {
            return ResponseEntity.ok(AdopcionView.toView(oAdopcion.get()));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("adopciones/urgentes")
    public ResponseEntity<?> getAllAdopcionesUrgentes() {
        List<AdopcionView> resultado = new ArrayList<>();
        adopcionService.findAll().stream().filter(x -> x.getEsUrgente() && !x.getEstado().equals(EstadoPublicacionAnimalEnum.FINALIZADA)).forEach(adopcion -> resultado.add(AdopcionView.toView(adopcion)));
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/adopciones")
    public ResponseEntity<?> crearPublicacionAdopcion(@RequestBody AdopcionDTO adopcionDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(adopcionService.saveDTO(adopcionDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @PutMapping("/adopciones")
    public ResponseEntity<?> modificarPublicacionAdopcion(@RequestBody AdopcionDTO adopcionDTO){
        try {
            adopcionService.saveDTO(adopcionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @PutMapping("/adopciones/{id}/cambiarEstado")
    public ResponseEntity<?> cambiarEstadoPublicacionAdopcion(@RequestBody String estado, @PathVariable Long id){
        try {
            Optional<Adopcion> optionalAdopcion = adopcionService.findById(id);
            if(optionalAdopcion.isEmpty()) return ResponseEntity.notFound().build();
            Adopcion adopcion = optionalAdopcion.get();
            adopcion.setEstado(EstadoPublicacionAnimalEnum.getEnum(estado));
            adopcionService.save(adopcion);
            if (adopcion.getEsUrgente() && adopcion.getEstado().equals(EstadoPublicacionAnimalEnum.FINALIZADA)){
                Refugio refugio = adopcion.getRefugio();
                refugio.setCantidadUrgentes(refugio.getCantidadUrgentes()-1);
                refugioService.save(refugio);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @PutMapping("/adopciones/{id}/cambiarUrgencia")
    public ResponseEntity<?> cambiarUrgenciaPublicacionAdopcion(@RequestBody Boolean urgencia, @PathVariable Long id){
        try {
            Optional<Adopcion> optionalAdopcion = adopcionService.findById(id);
            if(optionalAdopcion.isEmpty()) return ResponseEntity.notFound().build();
            Adopcion adopcion = optionalAdopcion.get();
            Refugio refugio = adopcion.getRefugio();
            if (adopcion.getEsUrgente() != urgencia){
                if (urgencia){
                    if (refugio.puedeAgregarUrgentes()){
                        adopcion.setEsUrgente(urgencia);
                        refugio.setCantidadUrgentes(refugio.getCantidadUrgentes() + 1);
                    }
                    else{
                        throw new RefugioException("El refugio no puede crear más publicaciones urgentes debido a que ha alcanzado el máximo permitido");
                    }
                }
                else{
                    adopcion.setEsUrgente(urgencia);
                    refugio.setCantidadUrgentes(refugio.getCantidadUrgentes() - 1);
                }
                adopcionService.save(adopcion);
                refugioService.save(refugio);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @PostMapping("/adopciones/{id}/postular")
    public ResponseEntity<?> postulacionAdopcion(@PathVariable Long id, @RequestBody FormularioDTO formularioDTO) {
        Optional<Adopcion> oAdopcion = adopcionService.findById(id);
        if (oAdopcion.isPresent()) {
            if (emailService.sendMailDTO(formularioDTO, oAdopcion.get())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/transitos")
    public ResponseEntity<?> getAllTransitos() {
        List<PublicacionAnimalCortaView> resultado = new ArrayList<>();
        transitoService.findAll().stream().filter(transito -> !transito.getEstado().equals(EstadoPublicacionAnimalEnum.FINALIZADA)).forEach(transito -> resultado.add(PublicacionAnimalCortaView.toView(transito)));
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/transitos/{id}")
    public ResponseEntity<?> getTransitoById(@PathVariable Long id) {
        Optional<Transito> oTransito = transitoService.findById(id);
        if (oTransito.isPresent()) {
            return ResponseEntity.ok(TransitoView.toView(oTransito.get()));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("transitos/urgentes")
    public ResponseEntity<?> getAllTransitosUrgentes() {
        List<TransitoView> resultado = new ArrayList<>();
        transitoService.findAll().stream().filter(x -> x.getEsUrgente()  && !x.getEstado().equals(EstadoPublicacionAnimalEnum.FINALIZADA)).forEach(transito -> resultado.add(TransitoView.toView(transito)));
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/transitos")
    public ResponseEntity<?> crearPublicacionTransito(@RequestBody TransitoDTO transitoDTO){
        try {
            Long idPublicacion = transitoService.saveDTO(transitoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(idPublicacion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @PutMapping("/transitos")
    public ResponseEntity<?> modificarPublicacionTransito(@RequestBody TransitoDTO transitoDTO){
        try {
            transitoService.saveDTO(transitoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @PutMapping("/transitos/{id}/cambiarEstado")
    public ResponseEntity<?> cambiarEstadoPublicacionTransito(@RequestBody String estado, @PathVariable Long id){
        try {
            Optional<Transito> optionalTransito = transitoService.findById(id);
            if(optionalTransito.isEmpty()) return ResponseEntity.notFound().build();
            Transito transito = optionalTransito.get();
            transito.setEstado(EstadoPublicacionAnimalEnum.getEnum(estado));
            transitoService.save(transito);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @PutMapping("/transitos/{id}/cambiarUrgencia")
    public ResponseEntity<?> cambiarUrgenciaPublicacionTransito(@RequestBody Boolean urgencia, @PathVariable Long id){
        try {
            Optional<Transito> optionalTransito = transitoService.findById(id);
            if(optionalTransito.isEmpty()) return ResponseEntity.notFound().build();
            Transito transito = optionalTransito.get();
            Refugio refugio = transito.getRefugio();
            if (transito.getEsUrgente() != urgencia){
                if (urgencia){
                    if (refugio.puedeAgregarUrgentes()){
                        transito.setEsUrgente(urgencia);
                        refugio.setCantidadUrgentes(refugio.getCantidadUrgentes() + 1);
                    }
                    else{
                        throw new RefugioException("El refugio no puede crear más publicaciones urgentes debido a que ha alcanzado el máximo permitido");
                    }
                }
                else{
                    transito.setEsUrgente(urgencia);
                    refugio.setCantidadUrgentes(refugio.getCantidadUrgentes() - 1);
                }
                transitoService.save(transito);
                refugioService.save(refugio);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @PostMapping("/transitos/{id}/postular")
    public ResponseEntity<?> postulacionTransito(@PathVariable Long id, @RequestBody FormularioDTO formularioDTO) {
        Optional<Transito> oTransito = transitoService.findById(id);
        if (oTransito.isPresent()) {
            if (emailService.sendMailDTO(formularioDTO,oTransito.get())){
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.internalServerError().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/voluntariados")
    public ResponseEntity<?> getAllVoluntariados() {
        List<VoluntariadoView> resultado = new ArrayList<>();
        voluntarioService.findAll().stream().filter(PublicacionVoluntariado::getEstaActiva).forEach(voluntariado -> resultado.add(VoluntariadoView.toView(voluntariado)));
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/voluntariados/{id}")
    public ResponseEntity<?> getVoluntariadoById(@PathVariable Long id) {
        Optional<PublicacionVoluntariado> oVoluntariado = voluntarioService.findById(id);
        if (oVoluntariado.isPresent()) {
            return ResponseEntity.ok(VoluntariadoView.toView(oVoluntariado.get()));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/voluntariados")
    public ResponseEntity<?> crearPublicacionVoluntariado(@RequestBody VoluntarioDTO voluntarioDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(voluntarioService.saveDTO(voluntarioDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @PutMapping("/voluntariados")
    public ResponseEntity<?> modificarPublicacionVoluntariado(@RequestBody VoluntarioDTO voluntarioDTO) {
        try {
            voluntarioService.saveDTO(voluntarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @PutMapping("/voluntariados/{id}/cambiarEstado")
    public ResponseEntity<?> cambiarEstadoPublicacionVoluntariado(@RequestBody Boolean estaActiva, @PathVariable Long id) {
        try {
            Optional<PublicacionVoluntariado> optionalVoluntariado = voluntarioService.findById(id);
            if (optionalVoluntariado.isEmpty()) return ResponseEntity.notFound().build();
            PublicacionVoluntariado publicacionVoluntariado = optionalVoluntariado.get();
            publicacionVoluntariado.setEstaActiva(estaActiva);
            voluntarioService.save(publicacionVoluntariado);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @PostMapping("/voluntariados/{id}/postular")
    public ResponseEntity<?> postulacionVoluntariado(@PathVariable Long id, @RequestBody FormularioDTO formularioDTO) {
        Optional<PublicacionVoluntariado> oVoluntariado = voluntarioService.findById(id);
        if (oVoluntariado.isPresent()) {
            if (emailService.sendMailDTO(formularioDTO, oVoluntariado.get())) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/donaciones")
    public ResponseEntity<?> getAllDonaciones() {
        List<DonacionView> resultado = new ArrayList<>();
        donacionService.findAll().stream().filter(PublicacionDonacion::getEstaActiva).forEach(donacion -> resultado.add(DonacionView.toView(donacion)));
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/donaciones/{id}")
    public ResponseEntity<?> getDonacionById(@PathVariable Long id) {
        Optional<PublicacionDonacion> oDonacion = donacionService.findById(id);
        if (oDonacion.isPresent()) {
            return ResponseEntity.ok(DonacionView.toView(oDonacion.get()));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/donaciones")
    public ResponseEntity<?> crearPublicacionDonacion(@RequestBody DonacionDTO donacionDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(donacionService.saveDTO(donacionDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @PutMapping("/donaciones")
    public ResponseEntity<?> modificarPublicacionDonacion(@RequestBody DonacionDTO donacionDTO){
        try {
            donacionService.saveDTO(donacionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @PutMapping("/donaciones/{id}/cambiarEstado")
    public ResponseEntity<?> cambiarEstadoPublicacionDonacion(@RequestBody Boolean estaActiva, @PathVariable Long id) {
        try {
            Optional<PublicacionDonacion> oDonacion = donacionService.findById(id);
            if(oDonacion.isEmpty()) return ResponseEntity.notFound().build();
            PublicacionDonacion publicacionDonacion = oDonacion.get();
            publicacionDonacion.setEstaActiva(estaActiva);
            donacionService.save(publicacionDonacion);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }
}

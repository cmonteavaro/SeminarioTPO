package ar.edu.uade.server.apiControllers;

import ar.edu.uade.server.DTO.AdopcionDTO;
import ar.edu.uade.server.DTO.DonacionDTO;
import ar.edu.uade.server.DTO.TransitoDTO;
import ar.edu.uade.server.DTO.VoluntarioDTO;
import ar.edu.uade.server.exceptions.RefugioException;
import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.model.Refugio;
import ar.edu.uade.server.service.RefugioService;
import ar.edu.uade.server.model.PublicacionDonacion;
import ar.edu.uade.server.model.Transito;
import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import ar.edu.uade.server.service.DonacionService;
import ar.edu.uade.server.service.TransitoService;
import ar.edu.uade.server.views.*;
import ar.edu.uade.server.model.PublicacionVoluntariado;
import ar.edu.uade.server.service.AdopcionService;
import ar.edu.uade.server.service.VoluntarioService;
import ar.edu.uade.server.views.AdopcionView;
import ar.edu.uade.server.views.VoluntariadoView;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionesApi {

    private final AdopcionService adopcionService;
    private final TransitoService transitoService;
    private final VoluntarioService voluntarioService;
    private final RefugioService refugioService;
    private final DonacionService donacionService;

    @Autowired
    public PublicacionesApi (VoluntarioService vs, AdopcionService as, TransitoService ts, RefugioService rs, DonacionService ds){
            this.adopcionService = as;
            this.voluntarioService = vs;
            this.transitoService = ts;
            this.refugioService = rs;
            this.donacionService = ds;
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

    @GetMapping("/voluntariados")
    public ResponseEntity<?> getAllVoluntariados() {
        List<VoluntariadoView> resultado = new ArrayList<>();
        voluntarioService.findAll().forEach(voluntariado -> resultado.add(VoluntariadoView.toView(voluntariado)));
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
            voluntarioService.saveDTO(voluntarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
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

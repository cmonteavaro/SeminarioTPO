package ar.edu.uade.server.apiControllers;

import ar.edu.uade.server.DTO.AdopcionDTO;
import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.model.Transito;
import ar.edu.uade.server.service.AdopcionService;
import ar.edu.uade.server.service.TransitoService;
import ar.edu.uade.server.views.PublicacionAnimalCortaView;
import ar.edu.uade.server.views.AdopcionView;
import ar.edu.uade.server.views.TransitoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionesApi {

    private AdopcionService adopcionService;
    private TransitoService transitoService;

    @Autowired
    public PublicacionesApi (AdopcionService as, TransitoService ts){
        this.adopcionService = as;
        this.transitoService = ts;
    }

    @GetMapping("/adopciones")
    public ResponseEntity<?> getAllAdopciones() {
        List<PublicacionAnimalCortaView> resultado = new ArrayList<>();
        adopcionService.findAll().forEach(adopcion -> resultado.add(PublicacionAnimalCortaView.toView(adopcion)));
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

    @PostMapping("/adopciones")
    public ResponseEntity<?> crearPublicacionAdopcion(@RequestBody AdopcionDTO adopcionDTO){
        try {
            adopcionService.saveDTO(adopcionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }

    @GetMapping("/transitos")
    public ResponseEntity<?> getAllTransitos() {
        List<PublicacionAnimalCortaView> resultado = new ArrayList<>();
        transitoService.findAll().forEach(transito -> resultado.add(PublicacionAnimalCortaView.toView(transito)));
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
}

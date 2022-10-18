package ar.edu.uade.server.apiControllers;

import ar.edu.uade.server.DTO.AdopcionDTO;
import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.service.AdopcionService;
import ar.edu.uade.server.views.AdopcionCortaView;
import ar.edu.uade.server.views.AdopcionView;
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

    @Autowired
    AdopcionService adopcionService;

    @GetMapping("/adopciones")
    public ResponseEntity<?> getAllAdopciones() {
        //List<AdopcionCortaView> resultado = new ArrayList<>();
        //adopcionService.findAll().forEach(adopcion -> resultado.add(AdopcionCortaView.toView(adopcion)));
        List<Adopcion> resultado = adopcionService.findAll();
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

    @PostMapping
    public ResponseEntity<?> crearPublicacionAdopcion(@RequestBody AdopcionDTO adopcionDTO){
        try {
            adopcionService.saveDTO(adopcionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }
}
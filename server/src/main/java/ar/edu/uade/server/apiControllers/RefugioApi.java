package ar.edu.uade.server.apiControllers;

import ar.edu.uade.server.model.*;
import ar.edu.uade.server.service.RefugioService;
import ar.edu.uade.server.views.PublicacionAnimalCortaView;
import ar.edu.uade.server.views.PerfilRefugioView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/refugios")
public class RefugioApi {

    @Autowired
    private RefugioService refugioService;

    @GetMapping("/{id}/perfil")
    public ResponseEntity<?> GetPerfilRefugio(@PathVariable Long id){
        Optional<Refugio> oRefugio = refugioService.findById(id);
        if (oRefugio.isPresent()){
            return ResponseEntity.ok(PerfilRefugioView.toView(oRefugio.get()));
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}/publicacionesAdopcion")
    public ResponseEntity<?> GetPublicacionesAdopcion(@PathVariable Long id){
        Optional<Refugio> oRefugio = refugioService.findById(id);
        if (oRefugio.isPresent()){
            List<Adopcion> adopciones = oRefugio.get().getPublicacionesAdopcion();
            return ResponseEntity.ok(PublicacionAnimalCortaView.toView((PublicacionAnimal) adopciones));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/publicacionesTransito")
    public ResponseEntity<?> GetPublicacionesTransito(@PathVariable Long id){
        Optional<Refugio> oRefugio = refugioService.findById(id);
        if (oRefugio.isPresent()){
            List<Transito> transitos = oRefugio.get().getPublicacionesTransito();
            return ResponseEntity.ok(PublicacionAnimalCortaView.toView((PublicacionAnimal) transitos));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/publicacionesVoluntariado")
    public void GetPublicacionesVoluntariado(@PathVariable Long id){
        //TODO Tenemos que ver que vamos a mostrar para poder realizar el metodo
    }

    @GetMapping("/{id}/publicacionesDonacion")
    public void GetPublicacionesDonacion(@PathVariable Long id){
        //TODO Tenemos que ver que vamos a mostrar para poder realizar el metodo
    }

    @PostMapping
    public ResponseEntity<?> crearRefugio(@RequestBody Refugio refugio) {
        try {
            refugioService.save(refugio);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }
}

package ar.edu.uade.server.apiControllers;

import ar.edu.uade.server.DTO.RefugioDTO;
import ar.edu.uade.server.exceptions.RefugioException;
import ar.edu.uade.server.model.*;
import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import ar.edu.uade.server.service.RefugioService;
import ar.edu.uade.server.views.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.ReferralException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin
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

    @GetMapping("/{id}/perfilCorto")
    public ResponseEntity<?> GetPerfilCortoRefugio(@PathVariable Long id){
        Optional<Refugio> oRefugio = refugioService.findById(id);
        if (oRefugio.isPresent()){
            return ResponseEntity.ok(PerfilCortoRefugioView.toView(oRefugio.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> GetAllPerfilCortoRefugio(){
        List<PerfilCortoRefugioView> pcorto = refugioService.findAll().stream().map(r -> PerfilCortoRefugioView.toView(r)).collect(Collectors.toList());
        return ResponseEntity.ok(pcorto);
    }

    @GetMapping("/{id}/publicacionesAdopcion")
    public ResponseEntity<?> getPublicacionesAdopcion(@PathVariable Long id){
        Optional<Refugio> oRefugio = refugioService.findById(id);
        if (oRefugio.isPresent()){
            List<PublicacionAnimalCortaView> resultado = new ArrayList<>();
            oRefugio.get().getPublicacionesAdopcion().stream().filter(adopcion -> !adopcion.getEstado().equals(EstadoPublicacionAnimalEnum.FINALIZADA)).forEach(adopcion -> resultado.add(PublicacionAnimalCortaView.toView(adopcion)));
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/publicacionesAdopcion/urgentes")
    public ResponseEntity<?> getPublicacionesAdopcionUrgentes(@PathVariable Long id) {
        Optional<Refugio> oRefugio = refugioService.findById(id);
        if (oRefugio.isPresent()){
            List<PublicacionAnimalCortaView> resultado = new ArrayList<>();
            oRefugio.get().getPublicacionesAdopcion().stream().filter(adopcion -> adopcion.getEsUrgente() && !adopcion.getEstado().equals(EstadoPublicacionAnimalEnum.FINALIZADA)).forEach(adopcion -> resultado.add(PublicacionAnimalCortaView.toView(adopcion)));
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/publicacionesTransito")
    public ResponseEntity<?> getPublicacionesTransito(@PathVariable Long id){
        Optional<Refugio> oRefugio = refugioService.findById(id);
        if (oRefugio.isPresent()){
            List<PublicacionAnimalCortaView> resultado = new ArrayList<>();
            oRefugio.get().getPublicacionesTransito().stream().filter(transito -> !transito.getEstado().equals(EstadoPublicacionAnimalEnum.FINALIZADA)).forEach(transito -> resultado.add(PublicacionAnimalCortaView.toView(transito)));
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/publicacionesTransito/urgentes")
    public ResponseEntity<?> getPublicacionesTransitoUrgentes(@PathVariable Long id) {
        Optional<Refugio> oRefugio = refugioService.findById(id);
        if (oRefugio.isPresent()){
            List<PublicacionAnimalCortaView> resultado = new ArrayList<>();
            oRefugio.get().getPublicacionesTransito().stream().filter(transito -> transito.getEsUrgente() && !transito.getEstado().equals(EstadoPublicacionAnimalEnum.FINALIZADA)).forEach(transito -> resultado.add(PublicacionAnimalCortaView.toView(transito)));
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/publicacionesVoluntariado")
    public ResponseEntity<?> GetPublicacionesVoluntariado(@PathVariable Long id){
        Optional<Refugio> oRefugio = refugioService.findById(id);
        if(oRefugio.isPresent()){
            List<VoluntariadoView> resultado = new ArrayList<>();
            oRefugio.get().getPublicacionesVoluntariado().stream().filter(PublicacionVoluntariado::getEstaActiva).forEach(voluntariado -> resultado.add(VoluntariadoView.toView(voluntariado)));
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/publicacionesDonacion")
    public ResponseEntity<?> GetPublicacionesDonacion(@PathVariable Long id){
        Optional<Refugio> oRefugio = refugioService.findById(id);
        if (oRefugio.isPresent()){
            List<DonacionView> resultado = new ArrayList<>();
            oRefugio.get().getPublicacionesDonacionesNoMonetarias().stream().filter(PublicacionDonacion::getEstaActiva).forEach(donacion -> resultado.add(DonacionView.toView(donacion)));
            return ResponseEntity.ok(resultado);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crearRefugio(@RequestBody RefugioDTO refugioDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(refugioService.save(refugioDTO.toModel()));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }
}

package ar.edu.uade.server.apiControllers;

import ar.edu.uade.server.DTO.AdopcionDTO;
import ar.edu.uade.server.DTO.FormularioDTO;
import ar.edu.uade.server.DTO.VoluntarioDTO;
import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.model.Transito;
import ar.edu.uade.server.service.TransitoService;
import ar.edu.uade.server.views.PublicacionAnimalCortaView;
import ar.edu.uade.server.views.TransitoView;
import ar.edu.uade.server.model.PublicacionVoluntariado;
import ar.edu.uade.server.model.Transito;
import ar.edu.uade.server.service.AdopcionService;
import ar.edu.uade.server.service.EmailServiceImpl;
import ar.edu.uade.server.service.VoluntarioService;
import ar.edu.uade.server.views.AdopcionView;
import ar.edu.uade.server.views.VoluntariadoCortaView;
import ar.edu.uade.server.views.VoluntariadoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionesApi {

    private AdopcionService adopcionService;
    private TransitoService transitoService;
    private VoluntarioService voluntarioService;
    private EmailServiceImpl emailService;

    @Autowired
    public PublicacionesApi (VoluntarioService vs, AdopcionService as, EmailServiceImpl es, TransitoService ts){
            this.adopcionService = as;
            this.voluntarioService = vs;
            this.emailService = es;
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

    @PutMapping("/adopciones")
    public ResponseEntity<?> modificarPublicacionAdopcion(@RequestBody AdopcionDTO adopcionDTO){
        try {
            adopcionService.saveDTO(adopcionDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }


    @GetMapping("/voluntariados")
    public ResponseEntity<?> getAllVoluntariados() {
        List<VoluntariadoCortaView> resultado = new ArrayList<>();
        voluntarioService.findAll().forEach(voluntariado -> resultado.add(VoluntariadoCortaView.toView(voluntariado)));
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

    @PostMapping("/adopciones/{id}/postular")
    public ResponseEntity<?> postulacionAdopcion(@PathVariable Long id, @RequestBody FormularioDTO formularioDTO) {
        Optional<Adopcion> oAdopcion = adopcionService.findById(id);
        if (oAdopcion.isPresent()) {
            if (emailService.sendMailDTO(formularioDTO,oAdopcion.get())){
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.internalServerError().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/transitos/{id}/postular")
    public ResponseEntity<?> postulacionTransito(@PathVariable Long id, @RequestBody FormularioDTO formularioDTO) {
        Optional<Transito> oTransito = null;
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

    @PostMapping("/voluntariados/{id}/postular")
    public ResponseEntity<?> postulacionVoluntariado(@PathVariable Long id, @RequestBody FormularioDTO formularioDTO) {
        Optional<PublicacionVoluntariado> oVoluntariado = voluntarioService.findById(id);
        if (oVoluntariado.isPresent()) {
            if (emailService.sendMailDTO(formularioDTO,oVoluntariado.get())){
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.internalServerError().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}

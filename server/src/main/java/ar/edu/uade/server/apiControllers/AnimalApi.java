package ar.edu.uade.server.apiControllers;

import ar.edu.uade.server.DTO.AnimalDTO;
import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.service.AnimalService;
import ar.edu.uade.server.views.AnimalView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/animales")
public class AnimalApi {

    @Autowired
    AnimalService animalService;

    @GetMapping
    public ResponseEntity<?> getAllAnimales() {
        return ResponseEntity.ok(AnimalView.toView(animalService.findAll()));
    }

    @PostMapping
    public ResponseEntity<?> crearAnimal(@RequestBody AnimalDTO animalDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(animalService.save(animalDTO.toModel()));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().eTag(e.getMessage()).build();
        }
    }
}

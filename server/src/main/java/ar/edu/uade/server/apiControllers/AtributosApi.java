package ar.edu.uade.server.apiControllers;


import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.model.PublicacionAnimal;
import ar.edu.uade.server.service.AdopcionService;
import ar.edu.uade.server.service.AnimalService;
import ar.edu.uade.server.views.AtributosView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/atributos")
public class AtributosApi {

    @GetMapping()
    public ResponseEntity<?> getAllAtributos () {
        return ResponseEntity.ok(AtributosView.toView());
    }

}

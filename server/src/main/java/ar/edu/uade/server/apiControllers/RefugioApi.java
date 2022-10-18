package ar.edu.uade.server.apiControllers;

import ar.edu.uade.server.model.Refugio;
import ar.edu.uade.server.service.RefugioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/refugios")
public class RefugioApi {

    @Autowired
    RefugioService refugioService;

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

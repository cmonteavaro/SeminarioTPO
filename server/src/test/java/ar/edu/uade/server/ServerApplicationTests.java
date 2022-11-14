package ar.edu.uade.server;

import ar.edu.uade.server.model.*;
import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import ar.edu.uade.server.model.enums.TamanioEnum;
import ar.edu.uade.server.model.enums.TipoAnimalEnum;
import ar.edu.uade.server.model.enums.TipoRedSocialEnum;
import ar.edu.uade.server.repository.RepositoryODB;
import ar.edu.uade.server.service.*;
//import ar.edu.uade.server.service.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import javax.sound.midi.Soundbank;
import java.sql.Ref;
import java.time.LocalDate;
import java.util.*;

@SpringBootTest
class ServerApplicationTests {

    @Autowired
    RefugioService refugioService;

    @Autowired
    AnimalService animalService;

    @Autowired
    AdopcionService adopcionService;

    @Autowired
    TransitoService transitoService;

    @Test
    void contextLoads() {
    }

    @Test
    public void asociarAdopcionesaRefugio() {
        for (Adopcion adopcion : adopcionService.findAll()) {
            System.out.println("ID Adopcion:" + adopcion.getId());
            System.out.println("ID Refugio:" + adopcion.getRefugio().getId());
            Optional<Refugio> optionalRefugio = refugioService.findById(adopcion.getRefugio().getId());
            if (optionalRefugio.isPresent()) {
                Refugio refugio = optionalRefugio.get();
                refugio.agregarPublicacionAdopcion(adopcion);
                refugioService.save(refugio);
                System.out.println("Publicacion agregada al refugio");
            }
        }
    }

    @Test
    void statsAnimales() {
        List<Animal> allAnimales= animalService.findAll();
        System.out.println("Cantidad de Animales: "+allAnimales.size());
    }

    @Test
    void statsRefugios() {
        for (Refugio r : refugioService.findAll()) {
            System.out.println("--------------------");
            System.out.println(r.getId());
            System.out.println(r.getNombre());
            System.out.println("Cantidad actual urgentes: "+r.getCantidadUrgentes());
            System.out.println("Cantidad maxima urgentes: "+(r.getPublicacionesAdopcion().size()+r.getPublicacionesTransito().size())*0.2);
            System.out.println("Pub adopcion: "+r.getPublicacionesAdopcion().size());
            System.out.println("Pub transito: "+r.getPublicacionesTransito().size());
            System.out.println("Pub donacion: "+r.getPublicacionesDonacionesNoMonetarias().size());
            System.out.println("Pub voluntariado: "+r.getPublicacionesVoluntariado().size());
        }
    }

    @Test
    void adopcionStats() {
        for (Adopcion r : adopcionService.findAll()) {
            System.out.println("----------------------------------");
            System.out.println("Id publicacion: "+r.getId());
            if ((r.getAnimal() == null)) {
                System.err.println("No tiene animal asignado");
            } else {
                System.out.println("Id animal: "+r.getAnimal().getId());
            }
            if ((r.getRefugio() == null)) {
                System.err.println("No tiene refugio asignado");
            } else {
                System.out.println("Id refugio: "+r.getRefugio().getId());
            }
        }
    }

    @Test
    void transitoStats() {
        for (Transito r : transitoService.findAll()) {
            System.out.println("----------------------------------");
            System.out.println("Id publicacion: "+r.getId());
            if ((r.getAnimal() == null)) {
                System.err.println("No tiene animal asignado");
            } else {
                System.out.println("Id animal: "+r.getAnimal().getId());
            }
            if ((r.getRefugio() == null)) {
                System.err.println("No tiene refugio asignado");
            } else {
                System.out.println("Id refugio: "+r.getRefugio().getId());
            }
        }
    }

    @Test
    void donacionStats() {
        for (PublicacionDonacion r : RepositoryODB.getInstancia().findAll(PublicacionDonacion.class)) {
            System.out.println("----------------------------------");
            System.out.println("Id publicacion: "+r.getId());
            if ((r.getRefugio() == null)) {
                System.err.println("No tiene refugio asignado");
            } else {
                System.out.println("Id refugio: "+r.getRefugio().getId());
            }
        }
    }

    @Test
    void voluntariadoStats() {
        for (PublicacionVoluntariado r : RepositoryODB.getInstancia().findAll(PublicacionVoluntariado.class)) {
            System.out.println("----------------------------------");
            System.out.println("Id publicacion: "+r.getId());
            if ((r.getRefugio() == null)) {
                System.err.println("No tiene refugio asignado");
            } else {
                System.out.println("Id refugio: "+r.getRefugio().getId());
            }
        }
    }
}

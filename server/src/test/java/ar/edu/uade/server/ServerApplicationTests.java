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
        System.out.println("Cantidad de imagenes por animal: ");
        allAnimales.forEach(a -> System.out.println(a.getId() + "-" + a.getNombre() + " (" + a.getTipoAnimal().getNombre() + "): " + a.getGaleriaImagenes().size()));
    }

    @Test
    void buscarAnimal() {
        Optional<Animal> oAnimal = animalService.findById(50L);
        if (oAnimal.isPresent()) {
            Animal animal = oAnimal.get();
            System.out.println("Nombre: " + animal.getNombre());
            System.out.println("Tamanio actual: " + animal.getTamanioActual().getNombre());
            System.out.println("Tamanio esperado: " + animal.getTamanioEsperado().getNombre());
            System.out.println("Edad: " + animal.getEdad());
            System.out.println("Imagenes: ");
            animal.getGaleriaImagenes().forEach(System.out::println);
        }

    }

    @Test
    void modificarAnimal() {
        Optional<Animal> optionalAnimal = animalService.findById(50L);
        if (optionalAnimal.isPresent()) {
            Animal animal = optionalAnimal.get();
            String imagen1 = "https://res.cloudinary.com/dypgglzvv/image/upload/v1668518955/Gato-blanco2_najjlp.jpg";
            String imagen2 = "https://res.cloudinary.com/dypgglzvv/image/upload/v1668518955/Gato-blanco1_p3ipyr.jpg";
            animal.agregarImagenes(imagen1, imagen2);
            animalService.save(animal);
        }
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
            r.getPublicacionesAdopcion().forEach(a -> System.out.println((a == null ? "Nulo" : a.getId())));
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

    @Test
    void getAllRefugios() {
        List<Refugio> refugios = refugioService.findAll();
        for (Refugio r: refugios) {
            System.out.println("Direccion:" + r.getDireccion().getProvincia() + ", " + r.getDireccion().getCalle() + ", " + r.getDireccion().getLocalidad());
        }
    }

    @Test
    void getAllAdopciones() {
        List<Adopcion> adopciones = adopcionService.findAll();
        for (Adopcion a: adopciones) {
            System.out.println("Localidad refugio: " + a.getRefugio().getDireccion().getLocalidad());
        }
    }

    @Test
    void getAllTransitos() {
        List<Transito> transitos = transitoService.findAll();
        for (Transito t: transitos) {
            System.out.println("ID: " + t.getId());
            System.out.println("Animal: " + t.getAnimal().getNombre());
            System.out.println("Localidad refugio: " + (t.getRefugio().getDireccion() == null ? "nulo" : t.getRefugio().getDireccion().getLocalidad()));
        }
    }

    @Test
    void eliminarTransitos() {
        RepositoryODB repositoryODB = RepositoryODB.getInstancia();

        List<Long> idsAEliminar = new ArrayList<>();
        idsAEliminar.add(24L);
        idsAEliminar.add(26L);
        idsAEliminar.add(27L);
        idsAEliminar.add(60L);
        idsAEliminar.forEach(id -> repositoryODB.deleteById(Transito.class, id));
    }
}

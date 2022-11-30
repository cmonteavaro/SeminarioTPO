package ar.edu.uade.server;

import ar.edu.uade.server.model.*;
import ar.edu.uade.server.model.enums.TipoVoluntariadoEnum;
import ar.edu.uade.server.repository.RepositoryODB;
import ar.edu.uade.server.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Autowired
    DonacionService donacionService;

    @Autowired
    VoluntarioService voluntariadoService;

    @Autowired
    UtilsServiceImpl utilsService;

    @Test
    void contextLoads() {
    }

    @Test
    public void normalizarPublicaciones(){
        for (Refugio refugio : refugioService.findAll()){
            refugio.setPublicacionesAdopcion(new ArrayList<>());
            refugio.setPublicacionesTransito(new ArrayList<>());
            refugio.setPublicacionesDonacionesNoMonetarias(new ArrayList<>());
            refugio.setPublicacionesVoluntariado(new ArrayList<>());
            refugioService.save(refugio);
        }
        asociarAdopcionesYRefugios();
        asociarTransitosYRefugios();
        asociarDonacionesYRefugios();
        asociarVoluntariadosYRefugios();
    }

    @Test
    public void asociarAdopcionesYRefugios() {
        for (Adopcion adopcion : adopcionService.findAll()) {
            System.out.println("---------------");
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
    public void asociarTransitosYRefugios() {
        for (Transito transito : transitoService.findAll()) {
            System.out.println("---------------");
            System.out.println("ID Transito:" + transito.getId());
            System.out.println("ID Refugio:" + transito.getRefugio().getId());
            Optional<Refugio> optionalRefugio = refugioService.findById(transito.getRefugio().getId());
            if (optionalRefugio.isPresent()) {
                Refugio refugio = optionalRefugio.get();
                refugio.agregarPublicacionTransito(transito);
                refugioService.save(refugio);
                System.out.println("Publicacion agregada al refugio");
            }
        }
    }
    @Test
    public void asociarDonacionesYRefugios() {
        for (PublicacionDonacion donacion : donacionService.findAll()) {
            System.out.println("---------------");
            System.out.println("ID Donacion:" + donacion.getId());
            System.out.println("ID Refugio:" + donacion.getRefugio().getId());
            Optional<Refugio> optionalRefugio = refugioService.findById(donacion.getRefugio().getId());
            if (optionalRefugio.isPresent()) {
                Refugio refugio = optionalRefugio.get();
                refugio.agregarPublicacionDonacion(donacion);
                refugioService.save(refugio);
                System.out.println("Publicacion agregada al refugio");
            }
        }
    }
    @Test
    public void asociarVoluntariadosYRefugios() {
        for (PublicacionVoluntariado voluntariado : voluntariadoService.findAll()) {
            System.out.println("---------------");
            System.out.println("ID Voluntariado:" + voluntariado.getId());
            System.out.println("ID Refugio:" + voluntariado.getRefugio().getId());
            Optional<Refugio> optionalRefugio = refugioService.findById(voluntariado.getRefugio().getId());
            if (optionalRefugio.isPresent()) {
                Refugio refugio = optionalRefugio.get();
                refugio.agregarPublicacionVoluntariado(voluntariado);
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
        Float lat = -34.724040474281765f;
        Float lon = -58.24418619275093f;
        for (Refugio r : refugioService.findAll()) {
            System.out.println("--------------------");
            System.out.println(r.getId());
            System.out.println(r.getNombre());
            System.out.println(r.getCorreo());
            System.out.println("Radio alcance: "+r.getRadioAlcance());
            System.out.println("Direccion: "+r.getDireccion().getCalle()+", "+r.getDireccion().getLocalidad()+", "+r.getDireccion().getProvincia());
            System.out.println("Latitud: "+r.getDireccion().getLatitud()+"     Longitud: "+r.getDireccion().getLongitud());
            System.out.println(utilsService.distanciaCoords(lat,lon,r.getDireccion().getLatitud(),r.getDireccion().getLongitud()));
            System.out.println("Cantidad actual urgentes: " + r.getCantidadUrgentes());
            System.out.println("Cantidad maxima urgentes: " + Math.ceil((r.getPublicacionesAdopcion().size() + r.getPublicacionesTransito().size()) * 0.2));
            System.out.println("Pub adopcion: " + r.getPublicacionesAdopcion().size());
            System.out.println("Pub transito: " + r.getPublicacionesTransito().size());
            System.out.println("Pub donacion: " + r.getPublicacionesDonacionesNoMonetarias().size());
            System.out.println("Pub voluntariado: " + r.getPublicacionesVoluntariado().size());
        }
    }

    @Test
    void modificarRefugio() {
        Optional<Refugio> optionalRefugio = refugioService.findById(39L);
        if (optionalRefugio.isPresent()) {
            Refugio refugio = optionalRefugio.get();
            refugio.getPerfilRefugio().setFotoPerfil("https://res.cloudinary.com/dypgglzvv/image/upload/v1669325610/SIPI-Refugios/zaguates-logo_mn9dho.jpg");
            refugio.getPerfilRefugio().setBanner("https://res.cloudinary.com/dypgglzvv/image/upload/v1669401146/SIPI-Refugios/zaguates-banner_ns6qbk.jpg");
            List<String> galeria = new ArrayList<>();
            galeria.add("https://res.cloudinary.com/dypgglzvv/image/upload/v1669477047/SIPI-Refugios/zaguates-galeria1_tx1j2x.jpg");
            galeria.add("https://res.cloudinary.com/dypgglzvv/image/upload/v1669477047/SIPI-Refugios/zaguates-galeria2_wkpmul.webp");
            galeria.add("https://res.cloudinary.com/dypgglzvv/image/upload/v1669477047/SIPI-Refugios/zaguates-galeria3_qtz1sg.jpg");
            //galeria.add("https://res.cloudinary.com/dypgglzvv/image/upload/v1669476239/SIPI-Refugios/adopciones-quilmes-galeria4_mvocrk.jpg");
            //galeria.add("https://res.cloudinary.com/dypgglzvv/image/upload/v1669476239/SIPI-Refugios/adopciones-quilmes-galeria1_hnlhvb.jpg");
            refugio.getPerfilRefugio().setGaleriaImagenes(galeria);
            refugioService.save(refugio);
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
            System.out.println("");
            System.out.println("-----------ADOPCION-------------");
            System.out.println("");
            System.out.println("Localidad refugio: " + a.getRefugio().getDireccion().getLocalidad());
            System.out.println("Tipo animal: "+a.getAnimal().getTipoAnimal());
            System.out.println("Descripcion animal: "+a.getDescripcion());
            System.out.println("Fecha publicacion: "+a.getFechaPublicacion());
            System.out.println("Nombre animal: "+a.getAnimal().getNombre());
            System.out.println("ID: "+a.getId());
        }
    }

    @Test
    void getAllTransitos() {
        List<Transito> transitos = transitoService.findAll();
        for (Transito t: transitos) {
            System.out.println("");
            System.out.println("-----------TRANSITO-------------");
            System.out.println("");
            System.out.println("ID: " + t.getId());
            System.out.println("Animal: " + t.getAnimal().getNombre());
            System.out.println("Localidad refugio: " + (t.getRefugio().getDireccion() == null ? "nulo" : t.getRefugio().getDireccion().getLocalidad()));
            System.out.println("Tipo animal: "+t.getAnimal().getTipoAnimal());
            System.out.println("Descripcion animal: "+t.getDescripcion());
            System.out.println("Fecha publicacion: "+t.getFechaPublicacion());
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

    @Test
    void getAllVoluntariados() {
        List<PublicacionVoluntariado> voluntariados = voluntariadoService.findAll();
        for (PublicacionVoluntariado v: voluntariados) {
            System.out.println("");
            System.out.println("-----------VOLUNTARIADO-------------");
            System.out.println("");
            System.out.println("ID: " + v.getId());
            System.out.println("Titulo: " + v.getTitulo());
            System.out.println("Descripcion: " + v.getDescripcion());
            System.out.println("Fecha publicacion: " + v.getFechaPublicacion());
            System.out.println("Tipo: "+v.getTipo());
            System.out.println("Refugio: "+v.getRefugio().getNombre());
        }
    }

    @Test
    void getAllDonaciones() {
        List<PublicacionDonacion> donaciones = donacionService.findAll();
        for (PublicacionDonacion d: donaciones) {
            System.out.println("");
            System.out.println("-----------DONACION-------------");
            System.out.println("");
            System.out.println("ID: " + d.getId());
            System.out.println("Titulo: " + d.getTitulo());
            System.out.println("Descripcion: " + d.getDescripcion());
            System.out.println("Fecha publicacion: " + d.getFechaPublicacion());
            System.out.println("Tipo: "+d.getTipo());
            System.out.println("Refugio: "+d.getRefugio().getNombre());
        }
    }

    @Test
    void pubVoluntariadosFix() {

        List<PublicacionVoluntariado> publicacionVoluntariados = voluntariadoService.findAll();
        for (PublicacionVoluntariado pv: publicacionVoluntariados){
            System.out.println(pv.getTipo());
        }

    }

    @Test
    void updateDireccionRefugios(){
        Optional<Refugio> oRefugio35 = refugioService.findById(35L);
        Optional<Refugio> oRefugio39 = refugioService.findById(39L);
        if(oRefugio35.isPresent() && oRefugio39.isPresent()){
            Refugio refugio35 = oRefugio35.get();
            Refugio refugio39 = oRefugio39.get();

            refugio35.setRadioAlcance(20);
            refugio39.setRadioAlcance(20);

//            Direccion direccionZaguates = new Direccion();
//            direccionZaguates.setLocalidad("Palermo");
//            direccionZaguates.setCalle("Juan Francisco Seguí");
//            direccionZaguates.setAltura(3775);
//            direccionZaguates.setProvincia("Buenos Aires");
//            direccionZaguates.setCodigoPostal("1425");
//            direccionZaguates.setLatitud(-34.57797868042211f);
//            direccionZaguates.setLongitud(-58.411349358867206f);
//            refugio39.setDireccion(direccionZaguates);
//
//            Direccion direccionPaticorti = new Direccion();
//            direccionPaticorti.setLocalidad("Palermo");
//            direccionPaticorti.setProvincia("Buenos Aires");
//            direccionPaticorti.setCodigoPostal("1425");
//            direccionPaticorti.setCalle("Avenida Santa Fe");
//            direccionPaticorti.setAltura(3880);
//            direccionPaticorti.setLatitud(-34.58383870461199f);
//            direccionPaticorti.setLongitud(-58.418122897358515f);
//            refugio35.setDireccion(direccionPaticorti);


//            System.out.println("-----REFUGIO 35------");
//            System.out.println(refugio35.getNombre());
//            System.out.println("Localidad: "+refugio35.getDireccion().getLocalidad());
//            System.out.println("Provincia: "+refugio35.getDireccion().getProvincia());
//            System.out.println("Codigo Postal: "+refugio35.getDireccion().getCodigoPostal());
//            System.out.println("Calle: "+refugio35.getDireccion().getCalle());
//            System.out.println("Altura: "+refugio35.getDireccion().getAltura());
//            System.out.println("Latitud: "+refugio35.getDireccion().getLatitud());
//            System.out.println("Longitud: "+refugio35.getDireccion().getLongitud());
//
//            System.out.println("-----REFUGIO 39------");
//            System.out.println(refugio39.getNombre());
//            System.out.println("Localidad: "+refugio39.getDireccion().getLocalidad());
//            System.out.println("Provincia: "+refugio39.getDireccion().getProvincia());
//            System.out.println("Codigo Postal: "+refugio39.getDireccion().getCodigoPostal());
//            System.out.println("Calle: "+refugio39.getDireccion().getCalle());
//            System.out.println("Altura: "+refugio39.getDireccion().getAltura());
//            System.out.println("Latitud: "+refugio39.getDireccion().getLatitud());
//            System.out.println("Longitud: "+refugio39.getDireccion().getLongitud());

            refugioService.save(refugio35);
            refugioService.save(refugio39);
        }

    }

    @Test
    public void fixdescripcion(){
        PublicacionVoluntariado pub = voluntariadoService.findById(56L).get();
        pub.setTitulo("Buscamos gente para pasear!");
        voluntariadoService.save(pub);
    }
}

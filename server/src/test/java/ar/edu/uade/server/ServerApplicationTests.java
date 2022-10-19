package ar.edu.uade.server;

import ar.edu.uade.server.model.*;
import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import ar.edu.uade.server.model.enums.TamanioEnum;
import ar.edu.uade.server.model.enums.TipoAnimalEnum;
import ar.edu.uade.server.model.enums.TipoRedSocialEnum;
import ar.edu.uade.server.repository.RepositoryODB;
import ar.edu.uade.server.service.AdopcionService;
import ar.edu.uade.server.service.AnimalService;
import ar.edu.uade.server.service.RefugioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import java.sql.Ref;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@SpringBootTest
class ServerApplicationTests {

    @Autowired
    RefugioService refugioService;

    @Autowired
    AnimalService animalService;

    @Autowired
    AdopcionService adopcionService;

    @Test
    void contextLoads() {
    }

    @Test
    void animalTest(){
//        ---- Creacion ----
//        Animal a = new Animal();
//        a.setNombre("Kali");
//        a.setTamanioActual(TamanioEnum.CHICO);
//        a.setTamanioEsperado(TamanioEnum.CHICO);
////        a.setFechaNac(new LocalDate(2021,11,21));
//        a.setTipoAnimal(TipoAnimalEnum.PERRO);
//        a.setCastrado(false);
//        a.setEsquemaCompletoVacunas(true);
//        a.setDesparasitado(true);
//        System.out.println( animalService.save(a));

        Animal a = new Animal();
        a.setNombre("Milanesa");
        a.setTamanioActual(TamanioEnum.CHICO);
        a.setTamanioEsperado(TamanioEnum.CHICO);
//        a.setFechaNac(new LocalDate(2021,11,21));
        a.setTipoAnimal(TipoAnimalEnum.PERRO);
        a.setCastrado(false);
        a.setEsquemaCompletoVacunas(true);
        a.setDesparasitado(true);
        Long l = animalService.save(a);
        System.out.println(l);

//        ---- Recuperacion de todos ----
//        for (Animal animal: animalService.findAll()){
//            System.out.println(animal.getNombre());
//            //System.out.println(a.getFechaNac().getDayOfMonth());
//            System.out.println(animal.getTamanioActual());
//            System.out.println(animal.getId());
//        };

//        ---- Recuperacion de uno ----
        /*
        Optional<Animal> oa = RepositoryODB.getInstancia().findById(Animal.class,1);
        if (oa.isPresent()) {
            Animal a = oa.get();
            System.out.println(a.getNombre());
            System.out.println(a.getFechaNac().getDay());
            System.out.println(a.getTamanioActual());
            System.out.println(a.getId());
        }else{
            System.err.println("Animal no encontrado");
        }
        */
    }

    @Test
    void refugioTest(){
        Refugio refugio = new Refugio();
        refugio.setNombre("AdopQAC");
        Direccion d = new Direccion();
        d.setCalle("Lima");
        d.setLocalidad("Monserrat");
        refugio.setDireccion(d);
        RedSocial rs1 = new RedSocial("https://ig.com", TipoRedSocialEnum.INSTAGRAM);
        RedSocial rs2 = new RedSocial("https://fb.com", TipoRedSocialEnum.FACEBOOK);
        refugio.agregarRedesSociales(rs1,rs2);
        System.out.println("Guardado con ID: "+refugioService.save(refugio).toString());
        for (Refugio r: refugioService.findAll()){
            System.out.println(r.getDireccion().getCalle());
            System.out.println(r.getId());
            System.out.println(r.getNombre());
            for (RedSocial rs : r.getRedesSociales()){
                System.out.println(rs.getLink());
                System.out.println(rs.getRedSocial());
            }
        };
    }

    @Test
    void AdopcionTestAislada(){
        Adopcion pub = new Adopcion();
//        pub.setAnimal(new Animal("Boneco", TipoAnimalEnum.PERRO));
        pub.setAnimal(animalService.findById((long) 1).get());
        pub.setEstado(EstadoPublicacionAnimalEnum.DISPONIBLE);
        pub.setDescripcion("Publicacion 70");
        pub.setNecesitaPatio(true);
        pub.agregarImagenes("/home/jdieguez/img1.jpg","/home/jdieguez/img2.jpg");
//        Refugio r = new Refugio("Adopciones Quilmes","adopq","quilmes");
        Refugio r = refugioService.findById((long)2).get();
        pub.setRefugio(r);
        r.agregarPublicacionAdopcion(pub);
        System.out.println("ID guardado: "+adopcionService.save(pub));
        System.out.println("ID refugio: "+refugioService.save(r));
        for (Adopcion pa: adopcionService.findAll()){
            System.out.println("-------- Adopcion ---------");
            System.out.println("ID: "+pa.getId());
            System.out.println("Estado: "+pa.getEstado());
            System.out.println("Patio: "+pa.getNecesitaPatio());
            System.out.println(pa.getRefugio().getNombre());
            System.out.println(pa.getAnimal().getNombre());
            //System.out.println("Dia: "+pa.getFechaPublicacion().getDayOfMonth());
            for (String img: pa.getGaleriaImagenes()){
                System.out.println("Imagen: "+img);
            }
        }

        for (Refugio re : refugioService.findAll()){
            System.out.println("-------- Refugio ---------");
            System.out.println(re.getNombre());
            System.out.println(re.getId());
            for (Adopcion ad: re.getPublicacionesAdopcion()){
                System.out.println("-------- Publicaciones ---------");
                System.out.println(ad.getDescripcion());
                System.out.println(ad.getId());
            }
        }
    }

    @Test
    public void AdopcionTestFull(){
//        RepositoryODB.getInstancia().deleteAll(Adopcion.class);
        Refugio r = RepositoryODB.getInstancia().findById(Refugio.class,6).get();
        Animal an = new Animal();
        an.setNombre("Boneco");
        an.setTipoAnimal(TipoAnimalEnum.PERRO);
        RepositoryODB.getInstancia().saveOBD(an);
        Adopcion pub = new Adopcion();
        pub.setAnimal(an);
        pub.setEstado(EstadoPublicacionAnimalEnum.DISPONIBLE);
        pub.setNecesitaPatio(true);
        pub.agregarImagenes("/home/jdieguez/img7.jpg");
        pub.setRefugio(r);
        r.agregarPublicacionAdopcion(pub);
        RepositoryODB.getInstancia().saveOBD(r);
        Refugio ref = RepositoryODB.getInstancia().findById(Refugio.class,6).get();
        System.out.println("------ Refugio -------");
        System.out.println("Nombre: "+ref.getNombre());
        for (Adopcion a: ref.getPublicacionesAdopcion()){
            System.out.println("--- Publicacion ---");
            System.out.println(a.getId());
            System.out.println(a.getAnimal().getNombre());
            System.out.println(a.getEstado());
        }

//        Refugio r = RepositoryODB.getInstancia().findById(Refugio.class,6).get();
//        r.setPublicacionesAdopcion(new ArrayList<>());
//        RepositoryODB.getInstancia().updateOBD(r);
    }

}

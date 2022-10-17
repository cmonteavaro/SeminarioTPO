package ar.edu.uade.server;

import ar.edu.uade.server.model.*;
import ar.edu.uade.server.model.enums.EstadoPublicacionAnimalEnum;
import ar.edu.uade.server.model.enums.TipoAnimalEnum;
import ar.edu.uade.server.model.enums.TipoRedSocialEnum;
import ar.edu.uade.server.repository.RepositoryODB;
import ar.edu.uade.server.service.RefugioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
class ServerApplicationTests {

    @Autowired
    RefugioService refugioService;

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
//        a.setFechaNac(new Date(2021,11,21));
//        a.setTipoAnimal(TipoAnimalEnum.PERRO);
//        a.setCastrado(false);
//        a.setEsquemaCompletoVacunas(true);
//        a.setDesparasitado(true);
//        RepositoryODB.getInstancia().saveOBD(a);

//        ---- Recuperacion de todos ----
        for (Animal a: RepositoryODB.getInstancia().findAll(Animal.class)){
            System.out.println(a.getNombre());
            System.out.println(a.getFechaNac().getDayOfMonth());
            System.out.println(a.getTamanioActual());
            System.out.println(a.getId());
        };

//        ---- Recuperacion de uno ----
//        Optional<Animal> oa = RepositoryODB.getInstancia().findById(Animal.class,1);
//        if (oa.isPresent()) {
//            Animal a = oa.get();
//            System.out.println(a.getNombre());
//            System.out.println(a.getFechaNac().getDay());
//            System.out.println(a.getTamanioActual());
//            System.out.println(a.getId());
//        }else{
//            System.err.println("Animal no encontrado");
//        }
    }

    @Test
    void refugioTest(){
        Refugio refugio = new Refugio();
        refugio.setNombre("Patitas Glew");
        Direccion d = new Direccion();
        d.setCalle("Lima");
        d.setLocalidad("Monserrat");
        refugio.setDireccion(d);
        RedSocial rs1 = new RedSocial("https://ig.com", TipoRedSocialEnum.INSTAGRAM);
        RedSocial rs2 = new RedSocial("https://fb.com", TipoRedSocialEnum.FACEBOOK);
        refugio.agregarRedesSociales(rs1,rs2);
        RepositoryODB.getInstancia().saveOBD(refugio);
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
        RepositoryODB.getInstancia().deleteAll(Adopcion.class);
        Adopcion pub = new Adopcion();
//        pub.setAnimal(new Animal("Boneco", TipoAnimalEnum.PERRO));
        pub.setAnimal(RepositoryODB.getInstancia().findById(Animal.class,1).get());
        pub.setEstado(EstadoPublicacionAnimalEnum.DISPONIBLE);
        pub.setNecesitaPatio(true);
        pub.agregarImagenes("/home/jdieguez/img1.jpg","/home/jdieguez/img2.jpg");
//        Refugio r = new Refugio("Adopciones Quilmes","adopq","quilmes");
        pub.setRefugio(RepositoryODB.getInstancia().findById(Refugio.class,6).get());
//        RepositoryODB.getInstancia().saveOBD(r);
        RepositoryODB.getInstancia().saveOBD(pub);
        for (String img: pub.getGaleriaImagenes()){
            System.out.println("Imagen: "+img);
        }
        for (Adopcion pa: RepositoryODB.getInstancia().findAll(Adopcion.class)){
            System.out.println("ID: "+pa.getId());
            System.out.println("Estado: "+pa.getEstado());
            System.out.println("Patio: "+pa.getNecesitaPatio());
            System.out.println(pa.getRefugio().getNombre());
            System.out.println(pa.getAnimal().getNombre());
            System.out.println("Dia: "+pa.getFechaPublicacion().getDayOfMonth());
            for (String img: pa.getGaleriaImagenes()){
                System.out.println("Imagen: "+img);
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
        RepositoryODB.getInstancia().updateOBD(r);
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

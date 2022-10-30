package ar.edu.uade.server.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.*;

@Repository
public class RepositoryODB {

    private static RepositoryODB instancia;
    private EntityManagerFactory emf;
    private EntityManager em;

    private RepositoryODB(){
        this.emf = null;
        this.em = null;
    }

    public static RepositoryODB getInstancia(){
        if(instancia == null)
            instancia = new RepositoryODB();
        return instancia;
    }

    //Abro DB - Si no existe la crea
    private void conexionOBD() {
        this.emf = Persistence.createEntityManagerFactory("db/Refugios.odb");
        this.em = this.emf.createEntityManager();
    }

    // Cierro DB
    private void desconexionOBD() {
        this.em.close();
        this.emf.close();
    }

    //Persistir
    public <T> T saveOBD(T aux){
        T retorno;
        this.conexionOBD();
        this.em.getTransaction().begin();
        retorno = this.em.merge(aux);
        this.em.getTransaction().commit();
        this.desconexionOBD();
        return retorno;
    }

    public <T> Optional<T> findById(Class<T> clase, long id){
        this.conexionOBD();
        Optional<T> resultado = Optional.ofNullable(em.find(clase,id));
        this.desconexionOBD();
        return resultado;
    }

    public <T> List<T> findAll(Class<T> clase){
        this.conexionOBD();
        TypedQuery<T> query = em.createQuery("SELECT x FROM "+clase.getName()+" x",clase);
        List<T> resultado = query.getResultList();
        this.desconexionOBD();
        return resultado;
    }

    public <T> void deleteById(Class<T> clase, long id){
        this.conexionOBD();
        em.getTransaction().begin();
        em.remove(em.find(clase,id));
        em.getTransaction().commit();
        this.desconexionOBD();
    }

    public <T> void deleteAll(Class<T> clase){
        this.conexionOBD();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM "+clase.getName()+" x").executeUpdate();
        em.getTransaction().commit();
        this.desconexionOBD();
    }

}

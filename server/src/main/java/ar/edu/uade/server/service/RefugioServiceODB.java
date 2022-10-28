package ar.edu.uade.server.service;

import ar.edu.uade.server.model.PublicacionAnimal;
import ar.edu.uade.server.model.Refugio;
import ar.edu.uade.server.repository.RepositoryODB;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RefugioServiceODB implements RefugioService{

    RepositoryODB repositoryODB = RepositoryODB.getInstancia();

    @Override
    public List<Refugio> findAll() {
        return repositoryODB.findAll(Refugio.class);
    }

    @Override
    public Optional<Refugio> findById(Long id) {
        return repositoryODB.findById(Refugio.class, id);
    }

    @Override
    public Long save(Refugio refugio) {
        return repositoryODB.saveOBD(refugio).getId();
    }

    public Boolean puedeAgregarUrgentes(Refugio refugio){
        Boolean habilitar = true;
        Integer cantidadPublicaciones = refugio.getPublicacionesAdopcion().size() + refugio.getPublicacionesTransito().size();
        if (cantidadPublicaciones>=30){
            if (refugio.getCantidadUrgentes() == 6){
                habilitar = false;
            }
        }
        else{
            Integer cantidadUrgentesAdmitidos = (int) Math.round((cantidadPublicaciones*0.2));
            if (cantidadUrgentesAdmitidos < (refugio.getCantidadUrgentes() + 1)){
                habilitar = false;
            }
        }
        return habilitar;
    }
}

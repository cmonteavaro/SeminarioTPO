package ar.edu.uade.server.service;

import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.repository.RepositoryODB;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdopcionServiceODB implements AdopcionService{

    @Override
    public List<Adopcion> findAll() {
        return RepositoryODB.getInstancia().findAll(Adopcion.class);
    }

    @Override
    public Optional<Adopcion> findById(Long id) {
        return RepositoryODB.getInstancia().findById(Adopcion.class,id);
    }

    @Override
    public void save(Adopcion adopcion) {
        RepositoryODB.getInstancia().saveOBD(adopcion);
    }
}

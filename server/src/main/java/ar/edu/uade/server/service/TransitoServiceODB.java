package ar.edu.uade.server.service;

import ar.edu.uade.server.model.Transito;
import ar.edu.uade.server.repository.RepositoryODB;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransitoServiceODB implements TransitoService {

    RepositoryODB repositoryODB = RepositoryODB.getInstancia();

    @Override
    public List<Transito> findAll() {
        return repositoryODB.findAll(Transito.class);
    }

    @Override
    public Optional<Transito> findById(Long id) {
        return repositoryODB.findById(Transito.class, id);
    }

    @Override
    public Long save(Transito transito) {
        return repositoryODB.saveOBD(transito).getId();
    }
}

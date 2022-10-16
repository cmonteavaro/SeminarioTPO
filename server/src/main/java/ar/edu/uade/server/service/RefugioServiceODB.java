package ar.edu.uade.server.service;

import ar.edu.uade.server.model.Refugio;
import ar.edu.uade.server.repository.RepositoryODB;

import java.util.List;
import java.util.Optional;

public class RefugioServiceODB implements RefugioService{

    @Override
    public List<Refugio> findAll() {
        return RepositoryODB.getInstancia().findAll(Refugio.class);
    }

    @Override
    public Optional<Refugio> findById(Long id) {
        return RepositoryODB.getInstancia().findById(Refugio.class, id);
    }
}

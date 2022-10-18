package ar.edu.uade.server.service;

import ar.edu.uade.server.model.Adopcion;

import java.util.List;
import java.util.Optional;

public interface AdopcionService {

    List<Adopcion> findAll();

    Optional<Adopcion> findById(Long id);

    void save(Adopcion adopcion);

}

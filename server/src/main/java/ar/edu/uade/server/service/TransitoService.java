package ar.edu.uade.server.service;

import ar.edu.uade.server.model.Transito;

import java.util.List;
import java.util.Optional;

public interface TransitoService {

    List<Transito> findAll();

    Optional<Transito> findById(Long id);

}

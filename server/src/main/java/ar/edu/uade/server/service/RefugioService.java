package ar.edu.uade.server.service;

import ar.edu.uade.server.model.Refugio;

import java.util.List;
import java.util.Optional;

public interface RefugioService {

    List<Refugio> findAll();

    Optional<Refugio> findById(Long id);

    Long save(Refugio refugio);

}

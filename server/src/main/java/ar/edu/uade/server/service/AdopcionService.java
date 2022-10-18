package ar.edu.uade.server.service;

import ar.edu.uade.server.DTO.AdopcionDTO;
import ar.edu.uade.server.exceptions.AnimalException;
import ar.edu.uade.server.exceptions.RefugioException;
import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.repository.RepositoryODB;

import java.util.List;
import java.util.Optional;

public interface AdopcionService {

    List<Adopcion> findAll();

    Optional<Adopcion> findById(Long id);

    void save(Adopcion adopcion);

    void saveDTO(AdopcionDTO adopcionDTO) throws AnimalException, RefugioException;

}

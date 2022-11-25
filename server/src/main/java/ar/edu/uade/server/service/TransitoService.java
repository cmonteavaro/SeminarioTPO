package ar.edu.uade.server.service;

import ar.edu.uade.server.DTO.AdopcionDTO;
import ar.edu.uade.server.DTO.TransitoDTO;
import ar.edu.uade.server.exceptions.AnimalException;
import ar.edu.uade.server.exceptions.RefugioException;
import ar.edu.uade.server.model.Transito;

import java.util.List;
import java.util.Optional;

public interface TransitoService {

    List<Transito> findAll();

    Optional<Transito> findById(Long id);

    Long save(Transito transito);

    Long saveDTO(TransitoDTO transitoDTO) throws AnimalException, RefugioException;

    Long updateDTO(TransitoDTO transitoDTO) throws AnimalException, RefugioException;

}

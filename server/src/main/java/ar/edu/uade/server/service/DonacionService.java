package ar.edu.uade.server.service;

import ar.edu.uade.server.DTO.DonacionDTO;
import ar.edu.uade.server.exceptions.DonacionException;
import ar.edu.uade.server.exceptions.RefugioException;
import ar.edu.uade.server.model.PublicacionDonacion;

import java.util.List;
import java.util.Optional;

public interface DonacionService {
    List<PublicacionDonacion> findAll();

    Optional<PublicacionDonacion> findById(Long id);

    Long save(PublicacionDonacion adopcion);

    Long saveDTO(DonacionDTO donacionDTO) throws DonacionException, RefugioException;
    Long updateDTO(DonacionDTO donacionDTO) throws DonacionException, RefugioException;
}

package ar.edu.uade.server.service;

import ar.edu.uade.server.DTO.VoluntarioDTO;
import ar.edu.uade.server.exceptions.RefugioException;
import ar.edu.uade.server.exceptions.VoluntarioException;
import ar.edu.uade.server.model.PublicacionVoluntariado;

import javax.naming.ReferralException;
import java.util.List;
import java.util.Optional;

public interface VoluntarioService {

    List<PublicacionVoluntariado> findAll();

    Optional<PublicacionVoluntariado> findById(Long Id);

    Long save(PublicacionVoluntariado publicacionVoluntariado);

    Long saveDTO(VoluntarioDTO voluntarioDTO) throws RefugioException;

    Long updateDTO(VoluntarioDTO voluntarioDTO) throws RefugioException;

}

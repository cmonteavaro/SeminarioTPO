package ar.edu.uade.server.service;

import ar.edu.uade.server.DTO.VoluntarioDTO;
import ar.edu.uade.server.exceptions.RefugioException;
import ar.edu.uade.server.exceptions.VoluntarioException;
import ar.edu.uade.server.model.PublicacionVoluntariado;
import ar.edu.uade.server.model.Refugio;
import ar.edu.uade.server.repository.RepositoryODB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoluntarioServiceODB implements VoluntarioService{

    RepositoryODB repositoryODB = RepositoryODB.getInstancia();

    private final RefugioService refugioService;

    @Autowired
    public VoluntarioServiceODB(RefugioService refugioService) {
        this.refugioService = refugioService;
    }

    @Override
    public List<PublicacionVoluntariado> findAll(){
        return repositoryODB.findAll(PublicacionVoluntariado.class);
    }

    @Override
    public Optional<PublicacionVoluntariado> findById(Long id){
        return repositoryODB.findById(PublicacionVoluntariado.class, id);
    }

    @Override
    public Long save(PublicacionVoluntariado publicacionVoluntariado){
        return repositoryODB.saveOBD(publicacionVoluntariado).getId();
    }

    @Override
    public Long saveDTO(VoluntarioDTO voluntarioDTO) throws RefugioException{
        PublicacionVoluntariado publicacionVoluntariado = voluntarioDTO.toModel();
        Optional<Refugio> oRefugio = refugioService.findById(voluntarioDTO.getIdRefugio());
        if(oRefugio.isEmpty()) throw new RefugioException("El refugio no fue encontrado");
        Refugio r = oRefugio.get();
        publicacionVoluntariado.setRefugio(r);
        Long idGuardado = this.save(publicacionVoluntariado);
        publicacionVoluntariado.setId(idGuardado);
        r.agregarPublicacionVoluntariado(publicacionVoluntariado);
        refugioService.save(r);
        return idGuardado;
    }

    public Long updateDTO(VoluntarioDTO voluntarioDTO) throws RefugioException{
        PublicacionVoluntariado publicacionVoluntariado = voluntarioDTO.toModel();
        Optional<Refugio> oRefugio = refugioService.findById(voluntarioDTO.getIdRefugio());
        if(oRefugio.isEmpty()) throw new RefugioException("El refugio no fue encontrado");
        Refugio r = oRefugio.get();
        publicacionVoluntariado.setRefugio(r);
        return this.save(publicacionVoluntariado);
    }
}

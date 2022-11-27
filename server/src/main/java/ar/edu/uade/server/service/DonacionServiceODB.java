package ar.edu.uade.server.service;

import ar.edu.uade.server.DTO.AdopcionDTO;
import ar.edu.uade.server.DTO.DonacionDTO;
import ar.edu.uade.server.exceptions.AnimalException;
import ar.edu.uade.server.exceptions.DonacionException;
import ar.edu.uade.server.exceptions.RefugioException;
import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.model.PublicacionDonacion;
import ar.edu.uade.server.model.Refugio;
import ar.edu.uade.server.repository.RepositoryODB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonacionServiceODB implements DonacionService{

    private final RefugioService refugioService;

    private RepositoryODB repositoryODB = RepositoryODB.getInstancia();

    @Autowired
    public DonacionServiceODB(RefugioService refugioService) {
        this.refugioService = refugioService;
    }

    @Override
    public List<PublicacionDonacion> findAll(){
        return repositoryODB.findAll(PublicacionDonacion.class);
    };

    @Override
    public Optional<PublicacionDonacion> findById(Long id) {
        return repositoryODB.findById(PublicacionDonacion.class, id);
    };

    @Override
    public Long save(PublicacionDonacion donacion){
        return repositoryODB.saveOBD(donacion).getId();
    };

    @Override
    public Long saveDTO(DonacionDTO donacionDTO) throws DonacionException{
        PublicacionDonacion donacion = donacionDTO.toModel();
        Optional<Refugio> oRefugio = refugioService.findById(donacionDTO.getIdRefugio());
        if (oRefugio.isEmpty()) throw new DonacionException("El refugio no fue encontrado");
        Refugio r = oRefugio.get();
        donacion.setRefugio(r);
        Long idGuardado = this.save(donacion);
        donacion.setId(idGuardado);
        r.agregarPublicacionDonacion(donacion);
        refugioService.save(r);
        return idGuardado;
    }

    @Override
    public Long updateDTO(DonacionDTO donacionDTO) throws DonacionException{
        PublicacionDonacion donacion = donacionDTO.toModel();
        Optional<Refugio> oRefugio = refugioService.findById(donacionDTO.getIdRefugio());
        if (oRefugio.isEmpty()) throw new DonacionException("El refugio no fue encontrado");
        Refugio r = oRefugio.get();
        donacion.setRefugio(r);
        return this.save(donacion);
    }
}

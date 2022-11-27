package ar.edu.uade.server.service;

import ar.edu.uade.server.DTO.AdopcionDTO;
import ar.edu.uade.server.DTO.TransitoDTO;
import ar.edu.uade.server.exceptions.AnimalException;
import ar.edu.uade.server.exceptions.RefugioException;
import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.model.Refugio;
import ar.edu.uade.server.model.Transito;
import ar.edu.uade.server.repository.RepositoryODB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransitoServiceODB implements TransitoService {

    RepositoryODB repositoryODB = RepositoryODB.getInstancia();

    private final AnimalService animalService;
    private final RefugioService refugioService;

    @Autowired
    public TransitoServiceODB(AnimalService animalService, RefugioService refugioService) {
        this.animalService = animalService;
        this.refugioService = refugioService;
    }

    @Override
    public List<Transito> findAll() {
        return repositoryODB.findAll(Transito.class);
    }

    @Override
    public Optional<Transito> findById(Long id) {
        return repositoryODB.findById(Transito.class, id);
    }

    @Override
    public Long save(Transito transito) {
        return repositoryODB.saveOBD(transito).getId();
    }

    @Override
    public Long saveDTO(TransitoDTO transitoDTO) throws AnimalException, RefugioException {
        Transito transito = transitoDTO.toModel();
        Optional<Animal> oAnimal = animalService.findById(transitoDTO.getIdAnimal());
        if (oAnimal.isEmpty()) throw new AnimalException("El animal no fue encontrado");
        transito.setAnimal(oAnimal.get());
        Optional<Refugio> oRefugio = refugioService.findById(transitoDTO.getIdRefugio());
        if (oRefugio.isEmpty()) throw new RefugioException("El refugio no fue encontrado");
        Refugio refugio = oRefugio.get();
        if (transito.getEsUrgente()){
            if (!refugio.puedeAgregarUrgentes()) throw new RefugioException("El refugio no puede crear más publicaciones urgentes debido a que ha alcanzado el máximo permitido");
            refugio.setCantidadUrgentes(refugio.getCantidadUrgentes()+1);
        }
        transito.setRefugio(refugio);
        Long idGuardado = this.save(transito);
        transito.setId(idGuardado);
        refugio.agregarPublicacionTransito(transito);
        refugioService.save(refugio);
        return idGuardado;
    }

    @Override
    public Long updateDTO(TransitoDTO transitoDTO) throws AnimalException, RefugioException {
        Transito transito = transitoDTO.toModel();
        Optional<Animal> oAnimal = animalService.findById(transitoDTO.getIdAnimal());
        if (oAnimal.isEmpty()) throw new AnimalException("El animal no fue encontrado");
        transito.setAnimal(oAnimal.get());
        Optional<Refugio> oRefugio = refugioService.findById(transitoDTO.getIdRefugio());
        if (oRefugio.isEmpty()) throw new RefugioException("El refugio no fue encontrado");
        Refugio refugio = oRefugio.get();
        if (transito.getEsUrgente()){
            if (!refugio.puedeAgregarUrgentes()) throw new RefugioException("El refugio no puede crear más publicaciones urgentes debido a que ha alcanzado el máximo permitido");
            refugio.setCantidadUrgentes(refugio.getCantidadUrgentes()+1);
        }
        transito.setRefugio(refugio);
        return this.save(transito);
    }
}

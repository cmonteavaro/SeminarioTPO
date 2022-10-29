package ar.edu.uade.server.service;

import ar.edu.uade.server.DTO.AdopcionDTO;
import ar.edu.uade.server.exceptions.AnimalException;
import ar.edu.uade.server.exceptions.RefugioException;
import ar.edu.uade.server.model.Adopcion;
import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.model.Refugio;
import ar.edu.uade.server.repository.RepositoryODB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdopcionServiceODB implements AdopcionService{

    RepositoryODB repositoryODB = RepositoryODB.getInstancia();

    private final AnimalService animalService;

    private final RefugioService refugioService;

    @Autowired
    public AdopcionServiceODB(AnimalService animalService, RefugioService refugioService) {
        this.animalService = animalService;
        this.refugioService = refugioService;
    }

    @Override
    public List<Adopcion> findAll() {
        return repositoryODB.findAll(Adopcion.class);
    }

    @Override
    public Optional<Adopcion> findById(Long id) {
        return repositoryODB.findById(Adopcion.class,id);
    }

    @Override
    public Long save(Adopcion adopcion) {
        return repositoryODB.saveOBD(adopcion).getId();
    }

    @Override
    public Long saveDTO(AdopcionDTO adopcionDTO) throws AnimalException, RefugioException {
        Adopcion adopcion = adopcionDTO.toModel();
        Optional<Animal> oAnimal = animalService.findById(adopcionDTO.getIdAnimal());
        if (oAnimal.isEmpty()) throw new AnimalException("El animal no fue encontrado");
        adopcion.setAnimal(oAnimal.get());
        Optional<Refugio> oRefugio = refugioService.findById(adopcionDTO.getIdRefugio());
        if (oRefugio.isEmpty()) throw new RefugioException("El refugio no fue encontrado");
        Refugio refugio = oRefugio.get();
        adopcion.setRefugio(refugio);
        refugio.agregarPublicacionAdopcion(adopcion);
        refugioService.save(refugio);
        return this.save(adopcion);
    }
}

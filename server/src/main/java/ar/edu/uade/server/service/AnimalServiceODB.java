package ar.edu.uade.server.service;

import ar.edu.uade.server.DTO.AnimalDTO;
import ar.edu.uade.server.model.Animal;
import ar.edu.uade.server.repository.RepositoryODB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceODB implements AnimalService {

    private RepositoryODB repositoryODB = RepositoryODB.getInstancia();

    public Long save(Animal animal) {
        return repositoryODB.saveOBD(animal).getId();
    }

    @Override
    public Optional<Animal> findById(Long id) {
        return repositoryODB.findById(Animal.class, id);
    }

    @Override
    public List<Animal> findAll() {
        return repositoryODB.findAll(Animal.class);
    }
}

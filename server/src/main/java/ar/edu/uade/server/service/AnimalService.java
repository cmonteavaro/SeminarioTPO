package ar.edu.uade.server.service;

import ar.edu.uade.server.model.Animal;

import java.util.List;
import java.util.Optional;

public interface AnimalService {

    Long save(Animal animal);

    Optional<Animal> findById(Long id);

    List<Animal> findAll();
}

package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findByName(String name);
    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();
}

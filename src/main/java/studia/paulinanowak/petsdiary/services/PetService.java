package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.commands.PetCommand;
import studia.paulinanowak.petsdiary.model.Pet;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PetService {
    Set<Pet> findAll();
    Set<Pet> findByUsername(String username);
    Pet findById(Long id);
    Collection<Pet> findByName(String name);
    PetCommand savePetCommand(PetCommand command);
    PetCommand findCommandById(Long id);

    void deleteById(Long id);
}

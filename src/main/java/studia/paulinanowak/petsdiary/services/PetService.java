package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.commands.PetCommand;
import studia.paulinanowak.petsdiary.model.Pet;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PetService {
    Set<Pet> findAll();
    Set<Pet> findByUsername(String username);
    Collection<Pet> findByName(String name);
    PetCommand savePetCommand(PetCommand command);
    PetCommand findCommandByUsernameAndId(String username, Long id);

    void deleteById(Long id);
    Pet findByUsernameAndId(String username, Long id);
}

package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.commands.PetCommand;
import studia.paulinanowak.petsdiary.model.Pet;

import java.util.Set;

public interface PetService {
    Set<Pet> findAll();
    Pet findById(Long id);
    PetCommand savePetCommand(PetCommand command);
    PetCommand findCommandById(Long id);

    void deleteById(Long id);
}

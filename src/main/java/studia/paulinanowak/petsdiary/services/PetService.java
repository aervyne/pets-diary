package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.commands.PetCommand;
import studia.paulinanowak.petsdiary.model.Pet;

public interface PetService extends CrudService<Pet, Long>{
    PetCommand savePetCommand(PetCommand command);
    PetCommand findCommandById(Long id);
}

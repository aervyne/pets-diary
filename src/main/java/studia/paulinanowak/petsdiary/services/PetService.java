package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.model.Pet;

import java.util.Set;

public interface PetService extends CrudService<Pet, Long>{
    Pet findByName(String name);
}

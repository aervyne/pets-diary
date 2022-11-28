package studia.paulinanowak.petsdiary.repositories;

import org.springframework.data.repository.CrudRepository;
import studia.paulinanowak.petsdiary.model.Pet;

import java.util.Set;

public interface PetRepository extends CrudRepository<Pet, Long> {
    Set<Pet> findPetsByUsername(String username);
}

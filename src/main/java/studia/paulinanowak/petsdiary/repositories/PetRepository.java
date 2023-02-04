package studia.paulinanowak.petsdiary.repositories;

import org.springframework.data.repository.CrudRepository;
import studia.paulinanowak.petsdiary.model.Pet;

import java.util.List;
import java.util.Optional;

public interface PetRepository extends CrudRepository<Pet, Long> {
    List<Pet> findPetsByUsername(String username);
    Optional<Pet> findPetByUsernameAndId(String username, Long id);
    void deletePetByUsernameAndId(String username, Long id);
}

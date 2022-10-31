package studia.paulinanowak.petsdiary.repositories;

import org.springframework.data.repository.CrudRepository;
import studia.paulinanowak.petsdiary.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}

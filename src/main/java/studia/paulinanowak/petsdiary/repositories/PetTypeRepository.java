package studia.paulinanowak.petsdiary.repositories;

import org.springframework.data.repository.CrudRepository;
import studia.paulinanowak.petsdiary.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}

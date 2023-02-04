package studia.paulinanowak.petsdiary.repositories;

import org.springframework.data.repository.CrudRepository;
import studia.paulinanowak.petsdiary.model.ControlMeasurement;
import studia.paulinanowak.petsdiary.model.Pet;

import java.util.List;

public interface ControlMeasurementRepository extends CrudRepository<ControlMeasurement, Long> {
    List<ControlMeasurement> findControlMeasurementsByPet(Pet pet);

}

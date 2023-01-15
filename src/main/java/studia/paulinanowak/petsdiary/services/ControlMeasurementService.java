package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.model.ControlMeasurement;
import studia.paulinanowak.petsdiary.model.Pet;

import java.util.Collection;
import java.util.Set;

public interface ControlMeasurementService {
    Set<ControlMeasurement> findAllByPets(Collection<Pet> pets);
}

package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.commands.ControlMeasurementCommand;
import studia.paulinanowak.petsdiary.model.ControlMeasurement;
import studia.paulinanowak.petsdiary.model.Pet;

import java.util.Collection;
import java.util.Set;

public interface ControlMeasurementService {
    Set<ControlMeasurement> findAllByPets(Collection<Pet> pets);
    void saveControlMeasurement(ControlMeasurementCommand controlMeasurementCommand);
    ControlMeasurement findById(Long id);
    ControlMeasurementCommand findCommandByUsernameAndId(Long id, String username);

    void deleteById(Long id, String username);
    Set<ControlMeasurement> findAllByPet(Pet pet);
}

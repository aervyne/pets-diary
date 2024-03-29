package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.commands.ControlMeasurementCommand;
import studia.paulinanowak.petsdiary.model.ControlMeasurement;
import studia.paulinanowak.petsdiary.model.Pet;

import java.util.Collection;
import java.util.List;

public interface ControlMeasurementService {
    List<ControlMeasurement> findAllByPets(Collection<Pet> pets);
    void saveControlMeasurement(ControlMeasurementCommand controlMeasurementCommand);
    ControlMeasurement findById(Long id);
    ControlMeasurementCommand findCommandByUsernameAndId(Long id, String username);

    void deleteById(Long id, String username);
    List<ControlMeasurement> findAllByPet(Pet pet);
}

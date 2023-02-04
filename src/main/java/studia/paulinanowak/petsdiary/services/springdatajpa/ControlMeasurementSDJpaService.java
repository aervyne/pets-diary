package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.commands.ControlMeasurementCommand;
import studia.paulinanowak.petsdiary.conventers.controlmeasurements.ControlMeasurementCommandToControlMeasurement;
import studia.paulinanowak.petsdiary.conventers.controlmeasurements.ControlMeasurementToControlMeasurementCommand;
import studia.paulinanowak.petsdiary.model.ControlMeasurement;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.repositories.ControlMeasurementRepository;
import studia.paulinanowak.petsdiary.services.ControlMeasurementService;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Profile("springdatajpa")
public class ControlMeasurementSDJpaService implements ControlMeasurementService {
    private final ControlMeasurementRepository controlMeasurementRepository;
    private final ControlMeasurementCommandToControlMeasurement controlMeasurementCommandToControlMeasurement;
    private final ControlMeasurementToControlMeasurementCommand controlMeasurementToControlMeasurementCommand;

    public ControlMeasurementSDJpaService(ControlMeasurementRepository controlMeasurementRepository,
                                          ControlMeasurementCommandToControlMeasurement controlMeasurementCommandToControlMeasurement,
                                          ControlMeasurementToControlMeasurementCommand controlMeasurementToControlMeasurementCommand) {
        this.controlMeasurementRepository = controlMeasurementRepository;
        this.controlMeasurementCommandToControlMeasurement = controlMeasurementCommandToControlMeasurement;
        this.controlMeasurementToControlMeasurementCommand = controlMeasurementToControlMeasurementCommand;
    }

    @Override
    public List<ControlMeasurement> findAllByPets(Collection<Pet> pets) {
        List<ControlMeasurement> controlMeasurements = new ArrayList<>();
        pets.forEach(pet -> controlMeasurements.addAll(controlMeasurementRepository.findControlMeasurementsByPet(pet)));

        return controlMeasurements;
    }

    @Override
    @Transactional
    public void saveControlMeasurement(ControlMeasurementCommand command) {
        ControlMeasurement detachedControlMeasurement = controlMeasurementCommandToControlMeasurement.convert(command);
        controlMeasurementRepository.save(detachedControlMeasurement);
    }

    @Override
    public ControlMeasurement findById(Long id) {
        return controlMeasurementRepository.findById(id).get();
    }

    @Override
    public ControlMeasurementCommand findCommandByUsernameAndId(Long id, String username) {
        ControlMeasurement controlMeasurement = findById(id);

        if (controlMeasurement.getPet().getUsername().equals(username)) {
            return controlMeasurementToControlMeasurementCommand.convert(controlMeasurement);
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteById(Long id, String username) {
        ControlMeasurement controlMeasurement = findById(id);

        if(username.equals(controlMeasurement.getPet().getUsername())) {
            controlMeasurementRepository.deleteById(id);
        } else {
            throw new RuntimeException("Nie można usunąć");
        }
    }

    @Override
    public List<ControlMeasurement> findAllByPet(Pet pet) {
        return controlMeasurementRepository.findControlMeasurementsByPet(pet);
    }
}

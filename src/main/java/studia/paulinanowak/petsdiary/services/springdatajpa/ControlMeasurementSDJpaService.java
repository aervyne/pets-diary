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
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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
    public Set<ControlMeasurement> findAllByPets(Collection<Pet> pets) {
        Set<ControlMeasurement> controlMeasurements = new HashSet<>();
        pets.forEach(pet -> controlMeasurements.addAll(controlMeasurementRepository.findControlMeasurementsByPet(pet)));

        return controlMeasurements;
    }

    @Override
    @Transactional
    public void saveControlMeasurement(ControlMeasurementCommand command) {
        ControlMeasurement detachedControlMeasurement = controlMeasurementCommandToControlMeasurement.convert(command);
        controlMeasurementRepository.save(detachedControlMeasurement);
    }
}

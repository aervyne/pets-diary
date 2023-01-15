package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.model.ControlMeasurement;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.repositories.ControlMeasurementRepository;
import studia.paulinanowak.petsdiary.services.ControlMeasurementService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class ControlMeasurementSDJpaService implements ControlMeasurementService {
    private final ControlMeasurementRepository controlMeasurementRepository;

    public ControlMeasurementSDJpaService(ControlMeasurementRepository controlMeasurementRepository) {
        this.controlMeasurementRepository = controlMeasurementRepository;
    }

    @Override
    public Set<ControlMeasurement> findAllByPets(Collection<Pet> pets) {
        Set<ControlMeasurement> controlMeasurements = new HashSet<>();
        pets.forEach(pet -> controlMeasurements.addAll(controlMeasurementRepository.findControlMeasurementsByPet(pet)));

        return controlMeasurements;
    }
}

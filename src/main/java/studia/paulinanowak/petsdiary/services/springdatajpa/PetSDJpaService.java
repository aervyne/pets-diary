package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.commands.PetCommand;
import studia.paulinanowak.petsdiary.conventers.PetCommandToPet;
import studia.paulinanowak.petsdiary.conventers.PetToPetCommand;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.repositories.PetRepository;
import studia.paulinanowak.petsdiary.services.PetService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetSDJpaService implements PetService {
    private final PetRepository petRepository;
    private final PetCommandToPet petCommandToPet;
    private final PetToPetCommand petToPetCommand;

    public PetSDJpaService(PetRepository petRepository, PetCommandToPet petCommandToPet, PetToPetCommand petToPetCommand) {
        this.petRepository = petRepository;
        this.petCommandToPet = petCommandToPet;
        this.petToPetCommand = petToPetCommand;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet findById(Long id) {
        Optional<Pet> petOptional = petRepository.findById(id);

        if(!petOptional.isPresent()) {
            throw new RuntimeException("Pet not found!");
        }

        return petOptional.get();
    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PetCommand savePetCommand(PetCommand command) {
        Pet detachedPet = petCommandToPet.convert(command);

        Pet savedPet = petRepository.save(detachedPet);
        return petToPetCommand.convert(savedPet);
    }

    @Override
    @Transactional
    public PetCommand findCommandById(Long id) {
        return petToPetCommand.convert(findById(id));
    }
}

package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.commands.PetCommand;
import studia.paulinanowak.petsdiary.conventers.PetCommandToPet;
import studia.paulinanowak.petsdiary.conventers.PetToPetCommand;
import studia.paulinanowak.petsdiary.errors.NotFoundException;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.repositories.PetRepository;
import studia.paulinanowak.petsdiary.services.PetService;

import javax.transaction.Transactional;
import java.util.*;

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
    public List<Pet> findAll() {
        List<Pet> pets = new ArrayList<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public List<Pet> findByUsername(String username) {
        List<Pet> pets = new ArrayList<>();
        petRepository.findPetsByUsername(username).forEach(pets::add);
        return pets;
    }

    @Override
    public Collection<Pet> findByName(String username, String name) {
        return this.findByUsername(username).stream().filter(pet -> pet.getName().equalsIgnoreCase(name)).toList();
    }

    @Override
    @Transactional
    public void deleteById(String username, Long id) {
        petRepository.deletePetByUsernameAndId(username, id);
    }

    @Override
    public Pet findByUsernameAndId(String username, Long id) {
        Optional<Pet> optionalPet = petRepository.findPetByUsernameAndId(username, id);

        if(!optionalPet.isPresent()) {
            throw  new NotFoundException();
        }

        return optionalPet.get();
    }

    @Override
    public Pet findById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        return pet.get();
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
    public PetCommand findCommandByUsernameAndId(String username, Long id) {
        return petToPetCommand.convert(findByUsernameAndId(username, id));
    }
}

package studia.paulinanowak.petsdiary.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.model.PetType;
import studia.paulinanowak.petsdiary.services.PetService;
import studia.paulinanowak.petsdiary.services.PetTypeService;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final PetService petService;
    private final PetTypeService petTypeService;

    public DataLoader(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setId(1L);
        dog.setType("Pies");

        PetType savedPetType = petTypeService.save(dog);

        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setBreed("Cavalier Spaniel");
        pet1.setPetType(savedPetType);
        pet1.setName("Luna");
        pet1.setDateOfBirth(LocalDate.parse("2022-05-14"));

        petService.save(pet1);
    }
}

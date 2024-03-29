package studia.paulinanowak.petsdiary.conventers.pets;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import studia.paulinanowak.petsdiary.commands.PetCommand;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.services.PetTypeService;

import java.time.LocalDate;

@Component
public class PetCommandToPet implements Converter<PetCommand, Pet> {
    private final PetTypeService petTypeService;

    public PetCommandToPet(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public Pet convert(PetCommand source) {
        if(source == null) {
            return null;
        }

        final Pet pet = new Pet();
        pet.setId(source.getId());
        pet.setName(source.getName());
        pet.setBreed(source.getBreed());
        pet.setPetType(petTypeService.findById(Long.valueOf(source.getPetTypeId())));
        pet.setDateOfBirth(LocalDate.parse(source.getDateOfBirth()));
        pet.setUsername(source.getUsername());
        pet.setFather(source.getFather());
        pet.setMother(source.getMother());
        pet.setMicrochipNumber(source.getMicrochipNumber());
        pet.setGender(source.getGender());

        return pet;
    }
}

package studia.paulinanowak.petsdiary.conventers.pets;

import io.micrometer.core.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import studia.paulinanowak.petsdiary.commands.PetCommand;
import studia.paulinanowak.petsdiary.model.Pet;

import java.time.LocalDate;

@Component
public class PetToPetCommand implements Converter<Pet, PetCommand> {

    @Synchronized
    @Nullable
    @Override
    public PetCommand convert(Pet source) {
        if (source == null) {
            return null;
        }

        PetCommand petCommand = new PetCommand();
        petCommand.setId(source.getId());
        petCommand.setPetTypeId(String.valueOf(source.getPetType().getId()));
        petCommand.setBreed(source.getBreed());
        petCommand.setDateOfBirth(String.valueOf(source.getDateOfBirth()));
        petCommand.setName(source.getName());
        petCommand.setImage(source.getImage());
        petCommand.setFather(source.getFather());
        petCommand.setMother(source.getMother());
        petCommand.setMicrochipNumber(source.getMicrochipNumber());
        petCommand.setUsername(source.getUsername());
        petCommand.setGender(source.getGender());

        return petCommand;
    }
}

package studia.paulinanowak.petsdiary.conventers;

import io.micrometer.core.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import studia.paulinanowak.petsdiary.commands.PetTypeCommand;
import studia.paulinanowak.petsdiary.model.PetType;

@Component
public class PetTypeToPetTypeCommand implements Converter<PetType, PetTypeCommand> {
    @Synchronized
    @Nullable
    @Override
    public PetTypeCommand convert(PetType source) {
        if (source == null) {
            return null;
        }

        final PetTypeCommand petTypeCommand = new PetTypeCommand();
        petTypeCommand.setId(source.getId());
        petTypeCommand.setType(source.getType());

        return petTypeCommand;
    }
}

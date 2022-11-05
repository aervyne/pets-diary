package studia.paulinanowak.petsdiary.conventers;

import io.micrometer.core.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import studia.paulinanowak.petsdiary.commands.PetTypeCommand;
import studia.paulinanowak.petsdiary.model.PetType;

@Component
public class PetTypeCommandToPetType implements Converter<PetTypeCommand, PetType> {
    @Synchronized
    @Nullable
    @Override
    public PetType convert(PetTypeCommand source) {
        if(source == null) {
            return null;
        }

        final PetType petType = new PetType();
        petType.setId(source.getId());
        petType.setType(source.getType());

        return petType;
    }
}

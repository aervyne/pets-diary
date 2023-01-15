package studia.paulinanowak.petsdiary.conventers.controlmeasurements;

import io.micrometer.core.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import studia.paulinanowak.petsdiary.commands.ControlMeasurementCommand;
import studia.paulinanowak.petsdiary.model.ControlMeasurement;

@Component
public class ControlMeasurementToControlMeasurementCommand implements Converter<ControlMeasurement, ControlMeasurementCommand> {
    @Synchronized
    @Nullable
    @Override
    public ControlMeasurementCommand convert(ControlMeasurement source) {
        if(source == null) {
            return null;
        }

        ControlMeasurementCommand command = new ControlMeasurementCommand();
        command.setId(source.getId());
        command.setPetId(source.getPet().getId().toString());
        command.setHeight(String.valueOf(source.getHeight()));
        command.setWeight(String.valueOf(source.getWeight()));
        command.setWeight(source.getDate().toString());

        return command;
    }
}

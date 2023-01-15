package studia.paulinanowak.petsdiary.conventers.controlmeasurements;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import studia.paulinanowak.petsdiary.commands.ControlMeasurementCommand;
import studia.paulinanowak.petsdiary.model.ControlMeasurement;
import studia.paulinanowak.petsdiary.services.PetService;

import java.time.LocalDate;

@Component
public class ControlMeasurementCommandToControlMeasurement implements Converter<ControlMeasurementCommand, ControlMeasurement> {
    private final PetService petService;

    public ControlMeasurementCommandToControlMeasurement(PetService petService) {
        this.petService = petService;
    }

    @Override
    public ControlMeasurement convert(ControlMeasurementCommand source) {
        if(source == null) {
            return null;
        }

        final ControlMeasurement controlMeasurement = new ControlMeasurement();
        controlMeasurement.setId(source.getId());
        controlMeasurement.setPet(petService.findById(Long.valueOf(source.getPetId())));
        controlMeasurement.setHeight(Double.parseDouble(source.getHeight()));
        controlMeasurement.setWeight(Double.parseDouble(source.getWeight()));
        controlMeasurement.setDate(LocalDate.parse(source.getDate()));

        return controlMeasurement;
    }
}

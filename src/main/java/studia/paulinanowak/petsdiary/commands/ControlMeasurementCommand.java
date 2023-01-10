package studia.paulinanowak.petsdiary.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ControlMeasurementCommand {
    private Long id;
    @NotBlank(message = "Wybierz zwierzę")
    private String petId;
    @NotBlank(message = "Wprowadź pomiar")
    private String height;
    @NotBlank(message = "Wprowadź pomiar")
    private String weight;
}

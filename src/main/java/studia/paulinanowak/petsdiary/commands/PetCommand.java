package studia.paulinanowak.petsdiary.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studia.paulinanowak.petsdiary.model.PetType;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PetCommand {
    private Long id;
    @NotBlank(message = "Imię nie może być puste.")
    private String name;
    @NotBlank(message = "Data nie może być pusta.")
    private String dateOfBirth;
    private String breed;
    private String petTypeId;
    private Byte[] image;
    private String username;
    private String mother;
    private String father;
    private String microchipNumber;
    private String gender;
}

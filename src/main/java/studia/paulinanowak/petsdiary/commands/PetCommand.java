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
    @NotBlank(message = "Wprowadź imię")
    private String name;
    @NotBlank(message = "Wprowadź datę")
    private String dateOfBirth;
    @NotBlank(message = "Wprowadż rasę")
    private String breed;
    @NotBlank(message = "Wybierz gatunek")
    private String petTypeId;
    private Byte[] image;
    private String username;
    @NotBlank(message = "Wprowadź imię matki")
    private String mother;
    @NotBlank(message = "Wprowadź imię ojca")
    private String father;
    @NotBlank(message = "Wprowadź numer mikroczipu")
    private String microchipNumber;
    @NotBlank(message = "Wybierz płeć")
    private String gender;
}

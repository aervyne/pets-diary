package studia.paulinanowak.petsdiary.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import studia.paulinanowak.petsdiary.model.PetType;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PetCommand {
    private Long petId;
    private String name;
    private String dateOfBirth;
    private String breed;
    private String petTypeId;
    private Byte[] image;
    private String username;
}

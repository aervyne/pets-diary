package studia.paulinanowak.petsdiary.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PetTypeCommand {
    private Long id;
    private String type;
}

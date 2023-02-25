package studia.paulinanowak.petsdiary.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserCommand {
    private Long id;
    @NotBlank(message = "Wprowadź e-mail")
    private String username;
    @NotBlank(message = "Wprowadź hasło")
    private String password;
    @NotBlank(message = "Powtórz hasło")
    private String repeatPassword;
    @NotBlank(message = "Wprowadź imię")
    private String firstname;
    @NotBlank(message = "Wprowadź nazwisko")
    private String lastname;
}

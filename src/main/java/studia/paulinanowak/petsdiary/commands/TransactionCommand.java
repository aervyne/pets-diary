package studia.paulinanowak.petsdiary.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class TransactionCommand {
    private Long id;
    private double value;
    private String date;
    private Long categoryId;
    private String note;
    private String username;
}

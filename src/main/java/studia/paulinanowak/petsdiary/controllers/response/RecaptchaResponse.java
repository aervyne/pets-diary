package studia.paulinanowak.petsdiary.controllers.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecaptchaResponse {
    Boolean success;
    String challege_ts;
    String hostname;
    Double score;
    String action;
}

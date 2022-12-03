package studia.paulinanowak.petsdiary.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Not found")  // 404
public class NotFoundException extends RuntimeException {
    // ...
}

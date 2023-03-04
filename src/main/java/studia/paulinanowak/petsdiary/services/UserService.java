package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.commands.UserCommand;
import studia.paulinanowak.petsdiary.model.User;

public interface UserService {
    void saveUserCommand(UserCommand command);
    UserCommand findByName(String username);
}

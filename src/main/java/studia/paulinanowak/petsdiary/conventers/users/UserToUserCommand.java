package studia.paulinanowak.petsdiary.conventers.users;

import io.micrometer.core.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import studia.paulinanowak.petsdiary.commands.PetCommand;
import studia.paulinanowak.petsdiary.commands.UserCommand;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.model.User;

@Component
public class UserToUserCommand implements Converter<User, UserCommand> {

    @Synchronized
    @Nullable
    @Override
    public UserCommand convert(User source) {
        if (source == null) {
            return null;
        }

        UserCommand userCommand = new UserCommand();
        userCommand.setId(source.getId());
        userCommand.setUsername(source.getUsername());
        userCommand.setFirstname(source.getFirstname());
        userCommand.setLastname(source.getLastname());
        userCommand.setPassword("");
        userCommand.setRepeatPassword("");

        return userCommand;
    }
}

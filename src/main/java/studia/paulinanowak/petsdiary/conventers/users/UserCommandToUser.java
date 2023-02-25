package studia.paulinanowak.petsdiary.conventers.users;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import studia.paulinanowak.petsdiary.commands.UserCommand;
import studia.paulinanowak.petsdiary.model.User;

@Component
public class UserCommandToUser implements Converter<UserCommand, User> {
    @Override
    public User convert(UserCommand source) {
        if(source == null) {
            return null;
        }

        final User user = new User();
        user.setId(source.getId());
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());
        user.setFirstname(source.getFirstname());
        user.setLastname(source.getLastname());

        return user;
    }
}

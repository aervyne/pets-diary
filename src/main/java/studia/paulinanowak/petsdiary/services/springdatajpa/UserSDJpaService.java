package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.commands.UserCommand;
import studia.paulinanowak.petsdiary.conventers.users.UserCommandToUser;
import studia.paulinanowak.petsdiary.conventers.users.UserToUserCommand;
import studia.paulinanowak.petsdiary.model.User;
import studia.paulinanowak.petsdiary.repositories.UserRepository;
import studia.paulinanowak.petsdiary.services.UserService;

import javax.transaction.Transactional;

@Service
@Profile("springdatajpa")
public class UserSDJpaService implements UserService {
    private final UserRepository userRepository;
    private final UserCommandToUser userCommandToUser;
    private final UserToUserCommand userToUserCommand;


    public UserSDJpaService(UserRepository userRepository, UserCommandToUser userCommandToUser,
                            UserToUserCommand userToUserCommand) {
        this.userRepository = userRepository;
        this.userCommandToUser = userCommandToUser;
        this.userToUserCommand = userToUserCommand;
    }

    @Override
    @Transactional
    public void saveUserCommand(UserCommand command) {
        User detachedUser = userCommandToUser.convert(command);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        detachedUser.setPassword(bCryptPasswordEncoder.encode(detachedUser.getPassword()));
        detachedUser.setRole("ROLE_USER");
        detachedUser.setEnabled(true);

        userRepository.save(detachedUser);
    }

    @Override
    public UserCommand findByName(String username) {
        UserCommand command = userToUserCommand.convert(userRepository.findUserByUsername(username));

        return command;
    }
}

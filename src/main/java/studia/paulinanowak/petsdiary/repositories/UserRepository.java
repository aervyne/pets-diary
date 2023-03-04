package studia.paulinanowak.petsdiary.repositories;

import org.springframework.data.repository.CrudRepository;
import studia.paulinanowak.petsdiary.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
}

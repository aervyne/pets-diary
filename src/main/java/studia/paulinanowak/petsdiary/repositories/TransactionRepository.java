package studia.paulinanowak.petsdiary.repositories;

import org.springframework.data.repository.CrudRepository;
import studia.paulinanowak.petsdiary.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findTransactionsByUsernameOrderByDateDesc(String username);
    void deleteTransactionByUsernameAndId(String username, Long id);
    Optional<Transaction> findByIdAndUsername(Long id, String username);
}

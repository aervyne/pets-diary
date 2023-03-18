package studia.paulinanowak.petsdiary.repositories;

import org.springframework.data.repository.CrudRepository;
import studia.paulinanowak.petsdiary.model.Transaction;
import studia.paulinanowak.petsdiary.model.TransactionCategory;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findTransactionsByUsername(String username);
    List<Transaction> findTransactionsByUsernameAndAndCategory(String username, TransactionCategory category);
}

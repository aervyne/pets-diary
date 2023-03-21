package studia.paulinanowak.petsdiary.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import studia.paulinanowak.petsdiary.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findTransactionsByUsernameOrderByDateDesc(String username);
    void deleteTransactionByUsernameAndId(String username, Long id);
    Optional<Transaction> findByIdAndUsername(Long id, String username);

    @Query("SELECT c.name, SUM(t.value) " +
            "FROM Transaction t " +
            "INNER JOIN TransactionCategory c ON t.category.id = c.id " +
            "WHERE c.categoryType = :#{#type} AND t.username = :#{#username} " +
            "GROUP BY c.name")
    List<Object> findTransactionsByCategoryType(String type, String username);
}

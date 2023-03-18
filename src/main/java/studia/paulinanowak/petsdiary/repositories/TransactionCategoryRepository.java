package studia.paulinanowak.petsdiary.repositories;

import org.springframework.data.repository.CrudRepository;
import studia.paulinanowak.petsdiary.model.TransactionCategory;

import java.util.List;
import java.util.Optional;

public interface TransactionCategoryRepository extends CrudRepository<TransactionCategory, Long> {
    List<TransactionCategory> findTransactionCategoriesByUsername(String username);
    Optional<TransactionCategory> findTransactionCategoryByUsernameAndId(String username, Long id);
    void deleteByUsernameAndId(String username, Long id);
}

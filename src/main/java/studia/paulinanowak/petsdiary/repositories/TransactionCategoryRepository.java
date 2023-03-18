package studia.paulinanowak.petsdiary.repositories;

import org.springframework.data.repository.CrudRepository;
import studia.paulinanowak.petsdiary.model.TransactionCategory;

import java.util.List;

public interface TransactionCategoryRepository extends CrudRepository<TransactionCategory, Long> {
    List<TransactionCategory> findTransactionCategoriesByUsername(String username);
}

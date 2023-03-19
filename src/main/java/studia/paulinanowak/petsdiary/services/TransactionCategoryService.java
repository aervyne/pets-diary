package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.model.TransactionCategory;

import java.util.List;
import java.util.Optional;

public interface TransactionCategoryService {
    List<TransactionCategory> findByUsername(String username);
    void saveCategory(TransactionCategory transactionCategory, String username);
    TransactionCategory findByUsernameAndId(String username, Long id);
    boolean deleteByUsernameAndId(String username, Long id);
    TransactionCategory findById(Long id);
}

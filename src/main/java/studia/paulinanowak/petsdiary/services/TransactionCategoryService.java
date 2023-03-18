package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.model.TransactionCategory;

import java.util.List;

public interface TransactionCategoryService {
    List<TransactionCategory> findByUsername(String username);
    void saveCategory(TransactionCategory transactionCategory, String username);
    TransactionCategory findByUsernameAndId(String username, Long id);
    void deleteByUsernameAndId(String username, Long id);
}

package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.model.TransactionCategory;

import java.util.List;

public interface TransactionCategoryService {
    List<TransactionCategory> findByUsername(String username);
}

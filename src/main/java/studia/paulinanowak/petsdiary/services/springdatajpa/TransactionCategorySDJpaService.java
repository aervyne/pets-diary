package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.model.Transaction;
import studia.paulinanowak.petsdiary.model.TransactionCategory;
import studia.paulinanowak.petsdiary.repositories.TransactionCategoryRepository;
import studia.paulinanowak.petsdiary.services.TransactionCategoryService;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class TransactionCategorySDJpaService implements TransactionCategoryService {
    private final TransactionCategoryRepository categoryRepository;

    public TransactionCategorySDJpaService(TransactionCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<TransactionCategory> findByUsername(String username) {
        List<TransactionCategory> categories = new ArrayList<>();
        categoryRepository.findTransactionCategoriesByUsername(username).forEach(categories::add);
        return categories.stream().sorted().toList();
    }
}

package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.errors.NotFoundException;
import studia.paulinanowak.petsdiary.model.Transaction;
import studia.paulinanowak.petsdiary.model.TransactionCategory;
import studia.paulinanowak.petsdiary.repositories.TransactionCategoryRepository;
import studia.paulinanowak.petsdiary.repositories.TransactionRepository;
import studia.paulinanowak.petsdiary.services.TransactionCategoryService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Profile("springdatajpa")
public class TransactionCategorySDJpaService implements TransactionCategoryService {
    private final TransactionCategoryRepository categoryRepository;
    private final TransactionRepository transactionRepository;

    public TransactionCategorySDJpaService(TransactionCategoryRepository categoryRepository,
                                           TransactionRepository transactionRepository) {
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionCategory> findByUsername(String username) {
        List<TransactionCategory> categories = new ArrayList<>();
        categoryRepository.findTransactionCategoriesByUsername(username).forEach(categories::add);
        return categories;
    }

    @Override
    public void saveCategory(TransactionCategory category, String username) {
        if (category.getCategoryType().equals("Wydatek") || category.getCategoryType().equals("Przychód")) {
            category.setUsername(username);
            categoryRepository.save(category);
        } else {
            throw new RuntimeException("Nieprawidłowy typ kategorii: " + category.getCategoryType());
        }
    }

    @Override
    public TransactionCategory findByUsernameAndId(String username, Long id) {
        Optional<TransactionCategory> categoryOptional = categoryRepository.findTransactionCategoryByUsernameAndId(username, id);

        if(!categoryOptional.isPresent()) {
            throw  new NotFoundException();
        }

        return categoryOptional.get();
    }

    @Transactional
    @Override
    public boolean deleteByUsernameAndId(String username, Long id) {
        List<Transaction> transactions = transactionRepository.findTransactionsByUsername(username);
        TransactionCategory category = categoryRepository.findTransactionCategoryByUsernameAndId(username, id).orElse(null);

        for (Transaction transaction : transactions) {
            if (transaction.getCategory().getId() == category.getId()) {
                return false;
            }
        }

        categoryRepository.deleteByUsernameAndId(username, id);

        return true;
    }
}

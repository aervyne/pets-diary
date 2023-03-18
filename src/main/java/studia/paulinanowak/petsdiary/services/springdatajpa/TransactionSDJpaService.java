package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.model.Transaction;
import studia.paulinanowak.petsdiary.repositories.TransactionRepository;
import studia.paulinanowak.petsdiary.services.TransactionService;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class TransactionSDJpaService implements TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionSDJpaService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> findByUsername(String username) {
        List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findTransactionsByUsername(username).forEach(transactions::add);
        return transactions.stream().sorted().toList();
    }
}

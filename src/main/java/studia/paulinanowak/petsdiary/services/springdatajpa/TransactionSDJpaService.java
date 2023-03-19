package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.commands.TransactionCommand;
import studia.paulinanowak.petsdiary.conventers.transactions.TransactionCommandToTransaction;
import studia.paulinanowak.petsdiary.conventers.transactions.TransactionToTransactionCommand;
import studia.paulinanowak.petsdiary.model.Transaction;
import studia.paulinanowak.petsdiary.repositories.TransactionRepository;
import studia.paulinanowak.petsdiary.services.TransactionService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class TransactionSDJpaService implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionCommandToTransaction transactionCommandToTransaction;
    private final TransactionToTransactionCommand transactionToTransactionCommand;

    public TransactionSDJpaService(TransactionRepository transactionRepository,
                                   TransactionCommandToTransaction transactionCommandToTransaction,
                                   TransactionToTransactionCommand transactionToTransactionCommand) {
        this.transactionRepository = transactionRepository;
        this.transactionCommandToTransaction = transactionCommandToTransaction;
        this.transactionToTransactionCommand = transactionToTransactionCommand;
    }

    @Override
    public List<Transaction> findByUsername(String username) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(transactionRepository.findTransactionsByUsernameOrderByDateDesc(username));
        return transactions;
    }

    @Transactional
    @Override
    public void deleteById(String username, Long id) {
        transactionRepository.deleteTransactionByUsernameAndId(username, id);
    }

    @Override
    public void saveTransaction(TransactionCommand command, String username) {
        Transaction detachedTransaction = transactionCommandToTransaction.convert(command);
        detachedTransaction.setUsername(username);
        transactionRepository.save(detachedTransaction);
    }

    @Override
    public TransactionCommand findCommandByUsernameAndId(String username, Long id) {
        Transaction transaction = transactionRepository.findByIdAndUsername(id, username).orElse(null);
        TransactionCommand command = transactionToTransactionCommand.convert(transaction);

        return command;
    }
}

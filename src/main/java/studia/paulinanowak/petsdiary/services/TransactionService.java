package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.commands.TransactionCommand;
import studia.paulinanowak.petsdiary.model.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findByUsername(String username);
    void deleteById(String username, Long id);
    void saveTransaction(TransactionCommand command, String username);
    TransactionCommand findCommandByUsernameAndId(String username, Long id);

}

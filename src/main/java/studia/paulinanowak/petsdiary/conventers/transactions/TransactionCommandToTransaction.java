package studia.paulinanowak.petsdiary.conventers.transactions;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import studia.paulinanowak.petsdiary.commands.TransactionCommand;
import studia.paulinanowak.petsdiary.model.Transaction;
import studia.paulinanowak.petsdiary.services.TransactionCategoryService;

import java.time.LocalDate;

@Component
public class TransactionCommandToTransaction implements Converter<TransactionCommand, Transaction> {
    private final TransactionCategoryService transactionCategoryService;

    public TransactionCommandToTransaction(TransactionCategoryService transactionCategoryService) {
        this.transactionCategoryService = transactionCategoryService;
    }

    @Override
    public Transaction convert(TransactionCommand source) {
        if(source == null) {
            return null;
        }

        final Transaction transaction = new Transaction();
        transaction.setId(source.getId());
        transaction.setUsername(source.getUsername());
        transaction.setCategory(transactionCategoryService.findById(source.getCategoryId()));
        transaction.setDate(LocalDate.parse(source.getDate()));
        transaction.setNote(source.getNote());
        transaction.setValue(source.getValue());

        return transaction;
    }
}

package studia.paulinanowak.petsdiary.conventers.transactions;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import studia.paulinanowak.petsdiary.commands.TransactionCommand;
import studia.paulinanowak.petsdiary.model.Transaction;

@Component
public class TransactionToTransactionCommand implements Converter<Transaction, TransactionCommand> {
    @Override
    public TransactionCommand convert(Transaction source) {
        if(source == null) {
            return null;
        }

        final TransactionCommand transaction = new TransactionCommand();
        transaction.setId(source.getId());
        transaction.setUsername(source.getUsername());
        transaction.setCategoryId(source.getCategory().getId());
        transaction.setDate(source.getDate().toString());
        transaction.setNote(source.getNote());
        transaction.setValue(source.getValue());

        return transaction;
    }
}

package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.services.TransactionCategoryService;

@Service
@Profile("springdatajpa")
public class TransactionCategorySDJpaService implements TransactionCategoryService {
}

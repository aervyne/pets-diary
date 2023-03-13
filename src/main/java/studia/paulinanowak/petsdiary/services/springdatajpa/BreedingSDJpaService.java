package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.model.Breeding;
import studia.paulinanowak.petsdiary.repositories.BreedingRepository;
import studia.paulinanowak.petsdiary.services.BreedingService;

@Service
@Profile("springdatajpa")
public class BreedingSDJpaService implements BreedingService {
    private final BreedingRepository breedingRepository;

    public BreedingSDJpaService(BreedingRepository breedingRepository) {
        this.breedingRepository = breedingRepository;
    }

    @Override
    public void createBreeding(String username) {
        Breeding breeding = new Breeding();
        breeding.setUsername(username);

        breedingRepository.save(breeding);
    }

    @Override
    public Breeding findByUsername(String username) {
        return breedingRepository.findByUsername(username);
    }

    @Override
    public void saveBreeding(Breeding breeding) {
        breedingRepository.save(breeding);
    }
}

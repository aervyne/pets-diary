package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.model.Breeding;

public interface BreedingService {
    void createBreeding(String username);
    Breeding findByUsername(String username);
    void saveBreeding(Breeding breeding);
}

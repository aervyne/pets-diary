package studia.paulinanowak.petsdiary.services;

import studia.paulinanowak.petsdiary.model.PetType;

import java.util.Set;

public interface PetTypeService {
    Set<PetType> findAll();
}

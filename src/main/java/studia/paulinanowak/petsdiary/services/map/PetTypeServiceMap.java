package studia.paulinanowak.petsdiary.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.model.PetType;
import studia.paulinanowak.petsdiary.services.PetTypeService;

import java.util.Set;

@Service
@Profile("map")
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(PetType object) {

    }

    @Override
    public PetType save(PetType object) {
        return null;
    }

    @Override
    public PetType findById(Long id) {
        return null;
    }
}

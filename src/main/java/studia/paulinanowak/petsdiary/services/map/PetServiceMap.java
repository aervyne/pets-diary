package studia.paulinanowak.petsdiary.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.model.PetType;
import studia.paulinanowak.petsdiary.services.CrudService;
import studia.paulinanowak.petsdiary.services.PetService;
import studia.paulinanowak.petsdiary.services.PetTypeService;

import java.util.Set;

@Service
@Profile("map")
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    private final PetTypeService petTypeService;

    public PetServiceMap(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Pet save(Pet object) {
        if(object != null) {
            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}

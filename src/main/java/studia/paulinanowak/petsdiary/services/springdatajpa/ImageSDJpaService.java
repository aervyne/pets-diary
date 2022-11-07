package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.repositories.PetRepository;
import studia.paulinanowak.petsdiary.services.ImageService;

import java.io.IOException;

@Service
@Profile("springdatajpa")
public class ImageSDJpaService implements ImageService {
    private final PetRepository petRepository;

    public ImageSDJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long petId, MultipartFile file) {

        try {
            Pet pet = petRepository.findById(petId).get();

            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }

            pet.setImage(byteObjects);

            petRepository.save(pet);
        } catch (IOException e) {
            //todo handle better
            System.out.println("ERROR: " + e.toString());

            e.printStackTrace();
        }
    }
}

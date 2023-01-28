package studia.paulinanowak.petsdiary.services.springdatajpa;

import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.repositories.PetRepository;
import studia.paulinanowak.petsdiary.services.ImageService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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

            // reads input image
            BufferedImage inputImage = ImageIO.read(file.getInputStream());

            BufferedImage bufferedImageResult = new BufferedImage(
                    350,
                    350,
                    inputImage.getType()
            );

            Graphics2D g2d = bufferedImageResult.createGraphics();
            g2d.drawImage(
                    inputImage,
                    0,
                    0,
                    350,
                    350,
                    null
            );
            g2d.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImageResult, FilenameUtils.getExtension(file.getOriginalFilename()), baos);
            byte[] bytes = baos.toByteArray();

            Byte[] byteObjects = new Byte[baos.toByteArray().length];

            int i = 0;

            for (byte b : baos.toByteArray()){
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

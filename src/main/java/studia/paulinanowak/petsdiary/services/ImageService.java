package studia.paulinanowak.petsdiary.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Long petId, MultipartFile file);
}

package studia.paulinanowak.petsdiary.security.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import studia.paulinanowak.petsdiary.commands.PetCommand;
import studia.paulinanowak.petsdiary.services.ImageService;
import studia.paulinanowak.petsdiary.services.PetService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;

@Controller
public class ImageController {
    private final ImageService imageService;
    private final PetService petService;

    public ImageController(ImageService imageService, PetService petService) {
        this.imageService = imageService;
        this.petService = petService;
    }

    @GetMapping("pet/image/{id}")
    public String showUploadForm(@PathVariable String id, Principal principal, Model model){
        model.addAttribute("pet", petService.findCommandByUsernameAndId(principal.getName(), Long.valueOf(id)));

        return "pets/imageUploadForm";
    }

    @PostMapping("pet/image/{id}")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){
        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/pets/show/" + id;
    }

    @GetMapping("pet/petimage/{id}")
    public void renderImageFromDB(@PathVariable String id, Principal principal, HttpServletResponse response) throws IOException {
        PetCommand petCommand = petService.findCommandByUsernameAndId(principal.getName(), Long.valueOf(id));

        if (petCommand.getImage() != null) {
            byte[] byteArray = new byte[petCommand.getImage().length];
            int i = 0;

            for (Byte wrappedByte : petCommand.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}

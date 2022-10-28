package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import studia.paulinanowak.petsdiary.services.PetService;
import studia.paulinanowak.petsdiary.services.PetTypeService;

@Controller
public class PetController {
    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @RequestMapping({"/pets", "/pets/index"})
    public String listPets(Model model) {
        model.addAttribute("pets", petService.findAll());
        return "pets/index";
    }
}

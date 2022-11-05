package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studia.paulinanowak.petsdiary.commands.PetCommand;
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

    @RequestMapping("/pets/show/{id}")
    public String showById(@PathVariable String id, Model model) {
        model.addAttribute("pet", petService.findById(Long.valueOf(id)));
        return "pets/show";
    }

    @RequestMapping("/pet/new")
    public String newPet(Model model) {
        model.addAttribute("pet", new PetCommand());
        model.addAttribute("petTypes", petTypeService.findAll());

        return "pets/petForm";
    }

    @PostMapping
    @RequestMapping("pet")
    public String saveOrUpdate(@ModelAttribute PetCommand command) {
        System.out.println(command.getPetTypeId());
        PetCommand savedCommand = petService.savePetCommand(command);

        return "redirect:/pets/show/" + savedCommand.getId();
    }
}

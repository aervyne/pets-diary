package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import studia.paulinanowak.petsdiary.commands.PetCommand;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.services.PetService;
import studia.paulinanowak.petsdiary.services.PetTypeService;

import java.security.Principal;
import java.util.Collection;

@Controller
public class PetController {
    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, PetTypeService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @GetMapping
    @RequestMapping({"/pets", "/pets/index"})
    public String listPets(Model model, Principal principal) {
        model.addAttribute("pets", petService.findByUsername(principal.getName()));
        model.addAttribute("pet", new Pet());
        return "pets/index";
    }

    @GetMapping
    @RequestMapping("/pets/show/{id}")
    public String showById(@PathVariable String id, Principal principal, Model model) {
        Pet pet = petService.findByUsernameAndId(principal.getName(), Long.valueOf(id));
        model.addAttribute("pet", pet);
        return "pets/show";
    }

    @GetMapping
    @RequestMapping("/pet/new")
    public String newPet(Model model) {
        model.addAttribute("pet", new PetCommand());
        model.addAttribute("petTypes", petTypeService.findAll());

        return "pets/petForm";
    }

    @GetMapping
    @RequestMapping("/pet/update/{id}")
    public String updatePet(@PathVariable String id, Principal principal, Model model) {
        PetCommand pet = petService.findCommandByUsernameAndId(principal.getName(), Long.valueOf(id));

        model.addAttribute("pet", pet);
        model.addAttribute("petTypes", petTypeService.findById(Long.valueOf(pet.getPetTypeId())));

        return "pets/petForm";
    }

    @PostMapping
    @RequestMapping("/pet")
    public String saveOrUpdate(PetCommand command, Principal principal) {
        command.setUsername(principal.getName());
        PetCommand savedCommand = petService.savePetCommand(command);

        return "redirect:/pets/show/" + savedCommand.getId();
    }

    @GetMapping
    @RequestMapping("/pet/delete/{id}")
    public String deletePet(@PathVariable String id, Principal principal){
        petService.deleteById(principal.getName(), Long.valueOf(id));

        return "redirect:/pets/index";
    }

    @GetMapping
    @RequestMapping("/pets/search")
    public String processFindForm(Pet pet, BindingResult result, Model model, Principal principal){
        // allow parameterless GET request for /pets to return all records
        System.out.println("Pet: " + pet.getName());
        if (pet.getName() == null) {
            pet.setName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        Collection<Pet> results = petService.findByName(principal.getName(), pet.getName().trim());
        System.out.println("Pet: " + pet.getName());

        if (results.size() == 1) {
            // 1 owner found
            pet = results.stream().findFirst().orElse(null);
            return "redirect:/pets/show/" + pet.getId();
        } else if(results.isEmpty()) {
            result.rejectValue("name", "notFound", "Not found!");
            return "pets/index";
        } else {
            // multiple owners found
            model.addAttribute("pets", results);
            return "pets/index";
        }
    }
}

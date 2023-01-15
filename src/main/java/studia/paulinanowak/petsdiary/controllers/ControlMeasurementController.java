package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import studia.paulinanowak.petsdiary.commands.ControlMeasurementCommand;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.services.ControlMeasurementService;
import studia.paulinanowak.petsdiary.services.PetService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@Controller
public class ControlMeasurementController {
    private final PetService petService;
    private final ControlMeasurementService controlMeasurementService;

    public ControlMeasurementController(PetService petService, ControlMeasurementService controlMeasurementService) {
        this.petService = petService;
        this.controlMeasurementService = controlMeasurementService;
    }

    @GetMapping
    @RequestMapping("/controlmeasurements")
    public String listMeasurement(Model model, Principal principal) {
        Set<Pet> pets = petService.findByUsername(principal.getName());
        model.addAttribute("measurements", controlMeasurementService.findAllByPets(pets));

        return "measurement/index";
    }

    @GetMapping
    @RequestMapping("/controlmeasurements/new")
    public String newControlMeasurement(Model model, Principal principal) {
        model.addAttribute("measurement", new ControlMeasurementCommand());
        model.addAttribute("pets", petService.findByUsername(principal.getName()));

        return "measurement/form";
    }

    @PostMapping
    @RequestMapping("/controlmeasurements/save")
    public String save(@Valid @ModelAttribute("measurement") ControlMeasurementCommand command, Errors errors,
                       Principal principal, Model model) {
        model.addAttribute("pets", petService.findByUsername(principal.getName()));

        if(errors.hasErrors()) {
            return "measurement/form";
        }

        controlMeasurementService.saveControlMeasurement(command);

        return "redirect:/controlmeasurements";
    }
}

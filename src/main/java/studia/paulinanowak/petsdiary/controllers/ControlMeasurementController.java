package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.services.ControlMeasurementService;
import studia.paulinanowak.petsdiary.services.PetService;

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
}

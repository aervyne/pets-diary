package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import studia.paulinanowak.petsdiary.commands.ControlMeasurementCommand;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.services.ControlMeasurementService;
import studia.paulinanowak.petsdiary.services.PetService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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
        List<Pet> pets = petService.findByUsername(principal.getName());
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

    @GetMapping
    @RequestMapping("/controlmeasurement/delete/{id}")
    public String delete(@PathVariable String id, Principal principal) {
        controlMeasurementService.deleteById(Long.valueOf(id), principal.getName());
        return "redirect:/controlmeasurements";
    }

    @GetMapping
    @RequestMapping("/controlmeasurements/update/{id}")
    public String update(@PathVariable String id, Principal principal, Model model) {
        ControlMeasurementCommand measurementCommand = controlMeasurementService.findCommandByUsernameAndId(Long.valueOf(id),
                principal.getName());

        model.addAttribute("measurement", measurementCommand);
        model.addAttribute("pets", petService.findByUsername(principal.getName()));

        return "measurement/form";
    }
}

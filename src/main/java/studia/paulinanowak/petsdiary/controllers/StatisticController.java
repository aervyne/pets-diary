package studia.paulinanowak.petsdiary.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studia.paulinanowak.petsdiary.commands.PetCommand;
import studia.paulinanowak.petsdiary.model.ControlMeasurement;
import studia.paulinanowak.petsdiary.model.Pet;
import studia.paulinanowak.petsdiary.services.ControlMeasurementService;
import studia.paulinanowak.petsdiary.services.PetService;

import java.security.Principal;
import java.util.Set;

@Controller
public class StatisticController {
    private final PetService petService;
    private final ControlMeasurementService controlMeasurementService;

    public StatisticController(PetService petService, ControlMeasurementService controlMeasurementService) {
        this.petService = petService;
        this.controlMeasurementService = controlMeasurementService;
    }

    @GetMapping
    @RequestMapping("/statistic/measurement")
    public String measurement(Model model, Principal principal) {
        model.addAttribute("chart", false);
        model.addAttribute("pets", petService.findByUsername(principal.getName()));
        model.addAttribute("selectedPet", new PetCommand());

        return "statistic/measurements";
    }
}

package studia.paulinanowak.petsdiary.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import studia.paulinanowak.petsdiary.model.ControlMeasurement;
import studia.paulinanowak.petsdiary.services.ControlMeasurementService;
import studia.paulinanowak.petsdiary.services.PetService;

import java.security.Principal;
import java.util.*;

@RestController
public class AjaxController {
    private final ControlMeasurementService controlMeasurementService;
    private final PetService petService;

    public AjaxController(ControlMeasurementService controlMeasurementService, PetService petService) {
        this.controlMeasurementService = controlMeasurementService;
        this.petService = petService;
    }

    @GetMapping("/controlmeasurements/{id}")
    List<ControlMeasurement> controlMeasurementsByPet(@PathVariable String id, Principal principal) {
        controlMeasurementService.findAllByPet(petService.findByUsernameAndId(principal.getName(), Long.valueOf(id)));
        List<ControlMeasurement> controlMeasurements = controlMeasurementService.findAllByPet(petService.findByUsernameAndId(principal.getName(),
                Long.valueOf(id))).stream().toList();

        return controlMeasurements;
    }


}

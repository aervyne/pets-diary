package studia.paulinanowak.petsdiary.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import studia.paulinanowak.petsdiary.model.ControlMeasurement;
import studia.paulinanowak.petsdiary.repositories.TransactionRepository;
import studia.paulinanowak.petsdiary.services.ControlMeasurementService;
import studia.paulinanowak.petsdiary.services.PetService;

import java.security.Principal;
import java.util.*;

@RestController
public class AjaxController {
    private final ControlMeasurementService controlMeasurementService;
    private final PetService petService;
    private final TransactionRepository transactionRepository;

    public AjaxController(ControlMeasurementService controlMeasurementService, PetService petService,
                          TransactionRepository transactionRepository) {
        this.controlMeasurementService = controlMeasurementService;
        this.petService = petService;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/controlmeasurements/{id}")
    List<ControlMeasurement> controlMeasurementsByPet(@PathVariable String id, Principal principal) {
        controlMeasurementService.findAllByPet(petService.findByUsernameAndId(principal.getName(), Long.valueOf(id)));
        List<ControlMeasurement> controlMeasurements = controlMeasurementService.findAllByPet(petService.findByUsernameAndId(principal.getName(),
                Long.valueOf(id))).stream().toList();

        return controlMeasurements;
    }

    @GetMapping("/expenses")
    List<Object> expenses(Principal principal) {
        List<Object> map = transactionRepository.findTransactionsByCategoryType("Wydatek", principal.getName())
                .stream()
                .toList();
        return map;
    }

    @GetMapping("/incomes")
    List<Object> incomes(Principal principal) {
        List<Object> map = transactionRepository.findTransactionsByCategoryType("Przychód", principal.getName())
                .stream()
                .toList();
        return map;
    }
}

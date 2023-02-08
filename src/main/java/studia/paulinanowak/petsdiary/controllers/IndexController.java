package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import studia.paulinanowak.petsdiary.model.Pet;

import java.security.Principal;

@Controller
public class IndexController {

    public IndexController() {
    }

    @GetMapping
    @RequestMapping("/index")
    public String listPets() {
        return "index";
    }
}

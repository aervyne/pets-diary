package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import studia.paulinanowak.petsdiary.model.Pet;

import java.security.Principal;

@Controller
public class IndexController {

    public IndexController() {
    }

    @GetMapping
    @RequestMapping("/")
    public String listPets() {
        return "index";
    }

    @GetMapping
    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping
    @RequestMapping("/login_error")
    public String loginError(Model model) {
        model.addAttribute("error", "Nieprawidłowy użytkownik lub hasło!");

        return "login";
    }
}

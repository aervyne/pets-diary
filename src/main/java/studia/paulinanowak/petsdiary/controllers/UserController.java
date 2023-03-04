package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import studia.paulinanowak.petsdiary.commands.UserCommand;
import studia.paulinanowak.petsdiary.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @RequestMapping("/user")
    public String userDetails(Model model, Principal principal) {
        model.addAttribute("user", userService.findByName(principal.getName()));

        return "user/details";
    }

    @PostMapping
    @RequestMapping("/user/save")
    public String saveUser(@Valid @ModelAttribute("user") UserCommand userCommand, Model model) {
        if(!userCommand.getPassword().equals(userCommand.getRepeatPassword())) {
            model.addAttribute("errorPassword", "Hasła nie są identyczne");
            model.addAttribute("user", userCommand);
            return "user/details";
        }

        userService.saveUserCommand(userCommand);
        model.addAttribute("info", "Zostałeś zarejestrowany, możnasz przejść do logowania");
        return "user/details";
    }
}

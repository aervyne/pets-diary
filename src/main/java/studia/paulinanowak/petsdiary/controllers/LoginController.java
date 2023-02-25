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

@Controller
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
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

    @GetMapping
    @RequestMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserCommand());

        return "register";
    }

    @PostMapping
    @RequestMapping("/register/save")
    public String saveUser(@Valid @ModelAttribute("user") UserCommand userCommand, Model model) {
        if(!userCommand.getPassword().equals(userCommand.getRepeatPassword())) {
            System.out.println(userCommand.getPassword());
            System.out.println(userCommand.getRepeatPassword());
            model.addAttribute("errorPassword", "Hasła nie są identyczne");
            model.addAttribute("user", userCommand);
            return "register";
        }

        userService.saveUserCommand(userCommand);
        model.addAttribute("info", "Zostałeś zarejestrowany, możnasz przejść do logowania");
        return "register";
    }
}

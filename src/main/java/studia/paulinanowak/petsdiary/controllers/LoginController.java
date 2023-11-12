package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studia.paulinanowak.petsdiary.commands.UserCommand;
import studia.paulinanowak.petsdiary.services.BreedingService;
import studia.paulinanowak.petsdiary.services.RecaptchaService;
import studia.paulinanowak.petsdiary.services.UserService;

import javax.validation.Valid;

@Controller
public class LoginController {
    private final UserService userService;
    private final BreedingService breedingService;
    private final RecaptchaService recaptchaService;

    public LoginController(UserService userService, BreedingService breedingService, RecaptchaService recaptchaService) {
        this.userService = userService;
        this.breedingService = breedingService;
        this.recaptchaService = recaptchaService;
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
    public String saveUser(@Valid @ModelAttribute(name = "user") UserCommand userCommand,
                           @RequestParam(name = "g-recaptcha-response") String captcha, Model model) {
        boolean captchaValid = recaptchaService.validateCaptcha(captcha);
        System.out.println(captcha);
        System.out.println(captchaValid);

        if(captchaValid) {
            if(!userCommand.getPassword().equals(userCommand.getRepeatPassword())) {
                System.out.println(userCommand.getPassword());
                System.out.println(userCommand.getRepeatPassword());
                model.addAttribute("errorPassword", "Hasła nie są identyczne");
                model.addAttribute("user", userCommand);
                return "register";
            }

            userService.saveUserCommand(userCommand);
            breedingService.createBreeding(userCommand.getUsername());

            model.addAttribute("info", "Zostałeś zarejestrowany, możnasz przejść do logowania");
            return "register";
        } else {
            model.addAttribute("errorCaptcha", "Captcha invalid.");
            return "register";
        }

    }
}

package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import studia.paulinanowak.petsdiary.services.AnnouncementService;

import java.security.Principal;

@Controller
public class AnnouncementController {
    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @GetMapping
    @RequestMapping({"/announcements/list"})
    public String listAnnoucementsForUser(Model model, Principal principal) {
        model.addAttribute("announcements", announcementService.findByUser(principal.getName()));
        model.addAttribute("view", 7);
        return "announcements/index";
    }
}

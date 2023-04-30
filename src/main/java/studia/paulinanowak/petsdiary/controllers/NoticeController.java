package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import studia.paulinanowak.petsdiary.services.NoticeService;

import java.security.Principal;

@Controller
public class NoticeController {
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping
    @RequestMapping({"/notices/list"})
    public String listAnnouncementsForUser(Model model, Principal principal) {
        model.addAttribute("announcements", noticeService.findByUser(principal.getName()));
        model.addAttribute("view", 4);
        return "notices/index";
    }
}

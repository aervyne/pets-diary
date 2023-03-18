package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import studia.paulinanowak.petsdiary.services.TransactionCategoryService;
import studia.paulinanowak.petsdiary.services.TransactionService;

import java.security.Principal;

@Controller
public class TransactionController {
    private final TransactionCategoryService categoryService;
    private final TransactionService transactionService;

    public TransactionController(TransactionCategoryService categoryService,
                                 TransactionService transactionService) {
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    @GetMapping
    @RequestMapping("/transactions")
    public String listTransactions(Model model, Principal principal) {
        model.addAttribute("transactions", transactionService.findByUsername(principal.getName()));
        model.addAttribute("view", 3);
        return "transactions/index";
    }
}

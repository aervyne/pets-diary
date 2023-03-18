package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import studia.paulinanowak.petsdiary.model.TransactionCategory;
import studia.paulinanowak.petsdiary.services.TransactionCategoryService;
import studia.paulinanowak.petsdiary.services.TransactionService;

import javax.validation.Valid;
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

    @GetMapping
    @RequestMapping("/transactions/categories")
    public String listCategories(Model model, Principal principal) {
        model.addAttribute("categories", categoryService.findByUsername(principal.getName()));
        model.addAttribute("view", 3);
        System.out.println(categoryService.findByUsername(principal.getName()).stream().toList().size());
        return "transactions/categories/index";
    }

    @GetMapping
    @RequestMapping("/transactions/categories/new")
    public String newCategory(Model model) {
        model.addAttribute("category", new TransactionCategory());
        model.addAttribute("view", 3);
        return "transactions/categories/form";
    }

    @PostMapping
    @RequestMapping("/transactions/categories/save")
    public String saveOrUpdate(@Valid @ModelAttribute("category") TransactionCategory category, Principal principal,
                               Model model) {
        model.addAttribute("view", 3);
        categoryService.saveCategory(category, principal.getName());

        return "redirect:/transactions/categories";
    }

    @GetMapping
    @RequestMapping("/transactions/category/update/{id}")
    public String updateCategory(@PathVariable String id, Principal principal, Model model) {
        TransactionCategory category = categoryService.findByUsernameAndId(principal.getName(), Long.valueOf(id));

        model.addAttribute("category", category);
        model.addAttribute("view", 3);
        return "transactions/categories/form";
    }
}

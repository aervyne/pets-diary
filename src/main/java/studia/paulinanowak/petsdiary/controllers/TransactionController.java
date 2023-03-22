package studia.paulinanowak.petsdiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import studia.paulinanowak.petsdiary.commands.TransactionCommand;
import studia.paulinanowak.petsdiary.model.Transaction;
import studia.paulinanowak.petsdiary.model.TransactionCategory;
import studia.paulinanowak.petsdiary.services.TransactionCategoryService;
import studia.paulinanowak.petsdiary.services.TransactionService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

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
    @RequestMapping("/transactions/delete/{id}")
    public String deleteTransaction(@PathVariable String id, Principal principal){
        transactionService.deleteById(principal.getName(), Long.valueOf(id));

        return "redirect:/transactions";
    }

    @GetMapping
    @RequestMapping("/transactions/new")
    public String newTransaction(Model model, Principal principal) {
        model.addAttribute("transaction", new TransactionCommand());
        model.addAttribute("categories", categoryService.findByUsername(principal.getName()));
        model.addAttribute("view", 3);
        return "transactions/form";
    }

    @GetMapping
    @RequestMapping("/transactions/update/{id}")
    public String updateTransaction(@PathVariable String id, Principal principal, Model model) {
        TransactionCommand transaction = transactionService.findCommandByUsernameAndId(principal.getName(), Long.valueOf(id));

        model.addAttribute("transaction", transaction);
        model.addAttribute("categories", categoryService.findByUsername(principal.getName()));
        model.addAttribute("view", 3);
        return "transactions/form";
    }

    @PostMapping
    @RequestMapping("/transactions/save")
    public String saveOrUpdateTransaction(@Valid @ModelAttribute("transaction") TransactionCommand transactionCommand,
                                          Principal principal,
                                          Model model) {
        model.addAttribute("view", 3);
        transactionService.saveTransaction(transactionCommand, principal.getName());

        return "redirect:/transactions";
    }

    @GetMapping
    @RequestMapping("/transactions/categories")
    public String listCategories(@RequestParam("error") Optional<String> error, Model model, Principal principal) {
        model.addAttribute("categories", categoryService.findByUsername(principal.getName()));
        model.addAttribute("error", error.orElse(null));
        model.addAttribute("view", 3);
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

    @GetMapping
    @RequestMapping("/transactions/category/delete/{id}")
    public String deleteCategory(@PathVariable String id, Principal principal){
        boolean deleteCategory = categoryService.deleteByUsernameAndId(principal.getName(), Long.valueOf(id));

        if (!deleteCategory) {
            return "redirect:/transactions/categories?error=delete";
        }

        return "redirect:/transactions/categories";
    }

    @GetMapping
    @RequestMapping("/transactions/statistic")
    public String statistic(){
        return "transactions/statistic";
    }

    @GetMapping("/users/export")
    public void exportToCSV(HttpServletResponse response, Principal principal) throws IOException {
        response.setContentType("text/csv");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; transactions.csv";
        response.setHeader(headerKey, headerValue);

        List<Transaction> transactionList = transactionService.findByUsername(principal.getName());

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"User ID", "E-mail", "Full Name", "Roles", "Enabled"};
        String[] nameMapping = {"id", "category", "value", "date", "note"};

        csvWriter.writeHeader(csvHeader);

        for (Transaction user : transactionList) {
            csvWriter.write(user, nameMapping);
        }

        csvWriter.close();

    }
}

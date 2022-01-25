package com.example.demo.controllers;

import com.example.demo.model.Quote;
import com.example.demo.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("quotes")
public class QuotesController {


    @Autowired
    public QuotesController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    QuoteService quoteService;

    @GetMapping("main")
    public String mainPage(){
        return "index";
    }

    @GetMapping("/list")
    public String getListOfQuotes(Model model) {
        List<Quote> quotes = quoteService.getAllQuotes();
        model.addAttribute("quotes",quotes);
        return "list";
    }

    @GetMapping("/add")
    public String displayAddQuoteForm(Model model){
        model.addAttribute("q",new Quote());
        return "addForm";
    }

    @PostMapping("/add")
    public String submitAddQuoteForm(@ModelAttribute Quote quote){
        quoteService.addQuote(quote);

        return "redirect:/quotes/list";
    }
    @GetMapping("/delete")
    public String displayDeleteQuoteForm(Model model){
        model.addAttribute("q",new Quote());
        return "deleteForm";
    }

    @PostMapping("/delete")
    public String submitDeleteQuoteForm(@ModelAttribute Quote quote){
        quoteService.deleteQuote(quote.getId());
        return "redirect:/quotes/list";
    }
    @PostMapping("/deleteAll")
    public String submitDeleteAllQuotesForm(){
       List<Quote> myList= quoteService.getAllQuotes();
       myList.forEach(quote -> quoteService.deleteQuote(quote.getId()));
        return "redirect:/quotes/list";
    }


    @GetMapping("/update")
    public String displayUpdateQuoteForm(Model model){
        model.addAttribute("q",new Quote());
        return "updateForm";
    }

    @PostMapping("/update")
    public String submitUpdateQuoteForm(@ModelAttribute Quote quote){
        quoteService.updateQuote(quote.getId(),quote);
        return "redirect:/quotes/list";
    }
}

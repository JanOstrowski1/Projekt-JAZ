package com.example.demo.controllers;

import com.example.demo.model.Quote;
import com.example.demo.service.QuoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Controller
@RequestMapping("quotes")
public class QuotesController {


    @Autowired
    public QuotesController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    QuoteService quoteService;

    @GetMapping("/list")
    public String getListOfQuotes(Model model) {
        List<Quote> quotes = quoteService.getAllQuotes();
        model.addAttribute("quotes",quotes);
        return "index";
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

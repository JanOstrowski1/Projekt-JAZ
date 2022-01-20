package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequestMapping("quote")
public class QuotesController {
    final String API_URL="http://localhost:8080";
    WebClient webClient = WebClient.create(API_URL);

    @GetMapping("get")
    public void getQuote(){

    }

}

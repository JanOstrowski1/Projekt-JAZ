package com.example.demo.controllers;

import com.example.demo.dto.QuoteDto;
import com.example.demo.model.Quote;
import com.example.demo.service.QuoteService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.DataInput;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
public class ZenApiController {

    @GetMapping("/quotes/random")
    public String getRandomQuoteFromZenApi(Model model) throws IOException,ParseException {
        final String sURL="https://zenquotes.io/api/random/[your_key]";
        QuoteDto quoteDto = ParseJsonFromURLtoQuoteDtoObject(sURL);
        model.addAttribute("q",quoteDto);
        return "random";
    }

    public QuoteDto QuoteOfTheDay() throws IOException, ParseException {
        final String sURL= "https://zenquotes.io/api/today/[your_key]";
        return ParseJsonFromURLtoQuoteDtoObject(sURL);
    }

    private QuoteDto ParseJsonFromURLtoQuoteDtoObject(String sURL) throws IOException, ParseException {
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(String.valueOf(IOUtils.toString(new URL(sURL))));
        String jsonString= String.valueOf(jsonArray.get(0));
        HashMap<String,String> map =
                new ObjectMapper().readValue(jsonString, HashMap.class);

        return new QuoteDto(map.get("q"),map.get("a"), map.get("h"));
    }
    @GetMapping("/quotes/author")
    public String fetchQuoteListFromZenApi(Model model) throws IOException, ParseException{
        final String sURL= "https://zenquotes.io/api/quotes/[your_key]";
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(String.valueOf(IOUtils.toString(new URL(sURL))));
        String jsonStr = jsonArray.toJSONString();
        Gson gson = new Gson();
        QuoteDto quoteDtoList[] = gson.fromJson(jsonStr, QuoteDto[].class);
        Arrays.stream(quoteDtoList).forEach(quoteDto -> quoteDto.setI(findImageUrl(quoteDto.getA())));

        model.addAttribute("list",quoteDtoList);
        return "author";
    }

//    public static void main(String[] args) throws IOException, ParseException {
//        final String sURL= "https://zenquotes.io/api/quotes/[your_key]";
//        JSONArray jsonArray = (JSONArray) new JSONParser().parse(String.valueOf(IOUtils.toString(new URL(sURL))));
//        String jsonStr = jsonArray.toJSONString();
//        Gson gson = new Gson();
//        QuoteDto quoteDtoList[] = gson.fromJson(jsonStr, QuoteDto[].class);
//        for (QuoteDto quoteDto:quoteDtoList) {
//            System.out.println("quote: "+quoteDto.getQ()+ " author: "+ quoteDto.getA() );
//        }
//
//    }
    public String findImageUrl(String author){
        String name= author.toLowerCase(Locale.ROOT).replace(' ','-');
        return "https://zenquotes.io/img/"+name +".jpg";
    }

}

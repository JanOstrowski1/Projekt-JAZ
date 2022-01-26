package com.example.demo.controllers;

import com.example.demo.dto.QuoteDto;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Controller
public class ZenApiController {

    @GetMapping("/quotes/random")
    public String getRandomQuoteFromZenApi(Model model) throws IOException, JSONException {
        final String sURL="https://zenquotes.io/api/random/[your_key]";
        JSONArray json = new JSONArray(IOUtils.toString(new URL(sURL), StandardCharsets.UTF_8));

        Gson gson = new Gson(); // Or use new GsonBuilder().create();
        QuoteDto quoteDto = gson.fromJson(String.valueOf(json),QuoteDto.class); // deserializes json into target2
        Type collectionType = new TypeToken<HashMap<String,String>>(){}.getType();
//        HashMap<String,String> values= gson.fromJson(String.valueOf(json), collectionType);
//        QuoteDto quoteDto= new QuoteDto(values.get("h"));
        model.addAttribute("q",quoteDto);
        return "random";
    }
}

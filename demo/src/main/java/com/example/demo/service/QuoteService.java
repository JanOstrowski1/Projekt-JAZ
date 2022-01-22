package com.example.demo.service;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.QuoteNotFoundException;
import com.example.demo.model.Quote;
import com.example.demo.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class QuoteService {
    QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public void addQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    public void updateQuote(int id, Quote newQuote) {
        if (quoteRepository.findById(id) != null) {
            Quote quote = quoteRepository.findById(id);
            if (Objects.equals(quote.getText(), null)) {
                throw new InvalidDataException();
            }

            quote.setText(newQuote.getText());

            if (Objects.equals(quote.getAuthor(), null)) {
                throw new InvalidDataException();
            }

            quote.setAuthor(newQuote.getAuthor());

            quoteRepository.save(quote);

            if (Objects.equals(quote.getImage_link(), null)) {
                throw new InvalidDataException();
            }

            quote.setImage_link(newQuote.getImage_link());

            quoteRepository.save(quote);

        } else {
            throw new QuoteNotFoundException();
        }
    }

    public void deleteQuote(int id) {
        quoteRepository.deleteById(id);
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

}

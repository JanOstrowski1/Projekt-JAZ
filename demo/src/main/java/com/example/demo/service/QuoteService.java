package com.example.demo.service;

import com.example.demo.exceptions.InvalidDataException;
import com.example.demo.exceptions.QuoteNotFoundException;
import com.example.demo.model.Quote;
import com.example.demo.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Component
public class QuoteService {
    QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
        fillUpTheQuoteList();
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

    public void fillUpTheQuoteList(){
        quoteRepository.save(new Quote("Albert Einstein","Life is like riding a bicycle. To keep your balance you must keep moving.","https://zenquotes.io/img/albert-einstein.jpg"));
        quoteRepository.save(new Quote("Voltaire","It is dangerous to be right in matters on which the established authorities are wrong.","https://zenquotes.io/img/voltaire.jpg"));
        quoteRepository.save(new Quote("Stephen Hawking","However difficult life may seem, there is always something you can do and succeed at.","https://zenquotes.io/img/stephen-hawking.jpg"));
        quoteRepository.save(new Quote("Helen Keller","The only thing worse than being blind is having sight but no vision.","https://zenquotes.io/img/helen-keller.jpg"));
        quoteRepository.save(new Quote("Charles Swindoll","We are all faced with a series of great opportunities brilliantly disguised as impossible situations.","https://zenquotes.io/img/charles-swindoll.jpg"));
        quoteRepository.save(new Quote("Ronald Reagan","Never let the things you can't do stop you from doing what you can.","https://www.history.com/.image/c_fit%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_620/MTU3ODc5MDg2NDM2NjU2NDU3/reagan_flags.jpg"));
        quoteRepository.save(new Quote("Sun Tzu","To know your Enemy, you must become your Enemy.","https://zenquotes.io/img/sun-tzu.jpg"));
    }

    public String findImageUrl(String author){
       String name= author.toLowerCase(Locale.ROOT).replace(' ','-');
       return "https://zenquotes.io/img/"+name +".jpg";
    }

}

package com.example.demo.repository;

import com.example.demo.dto.QuoteDto;
import com.example.demo.model.Quote;
import org.springframework.data.repository.CrudRepository;

public interface ZenQuoteRepository extends CrudRepository<Quote,Integer> {
}

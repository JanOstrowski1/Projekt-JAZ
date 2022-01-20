package com.example.demo.repository;

import com.example.demo.model.Quote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote,Integer> {
    public Quote findById(int id);
    public List<Quote> findAll();
}

package com.example.demo.repository;

import com.example.demo.model.Quote;
import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findByNickname(String nickname);
    Boolean existsByNickname(String nickname);
}

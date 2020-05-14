package com.example.demo.interfaces;

import com.example.demo.entities.User;

import java.util.List;
import java.util.Optional;

public interface IUser {
    Iterable<User> findAll();

    Optional<User> findById(Integer userId);

    List<User> findByNickname(String nickname);

    void save(User user);
}

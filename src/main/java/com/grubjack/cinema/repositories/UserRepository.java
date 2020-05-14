package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByNickname(String nickname);

}
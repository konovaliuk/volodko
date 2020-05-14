package com.grubjack.cinema.dao.repositories;

import com.grubjack.cinema.dao.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
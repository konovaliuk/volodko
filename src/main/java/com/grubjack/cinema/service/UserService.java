package com.grubjack.cinema.service;

import com.grubjack.cinema.dao.entities.User;
import com.grubjack.cinema.exception.DaoException;
import com.grubjack.cinema.model.User;

import java.util.List;

public interface UserService {
    boolean checkLogin(String login, String password);
    User getByEmail(String email);
    List<User> findAll();
    void delete(int userId);
    void create(User user);
}

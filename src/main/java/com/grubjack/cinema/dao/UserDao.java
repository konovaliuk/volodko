package com.grubjack.cinema.dao;

import com.grubjack.cinema.exception.DaoException;
import com.grubjack.cinema.model.User;


public interface UserDao extends BaseDao<User> {
    User getByEmail(String email) throws DaoException;
}

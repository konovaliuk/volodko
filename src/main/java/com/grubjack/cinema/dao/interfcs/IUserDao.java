package com.grubjack.cinema.dao.interfcs;

import com.grubjack.cinema.dao.entities.*;

public interface IUserDao{
    User findByEmail(String email);
}

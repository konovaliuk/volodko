package com.grubjack.cinema.dao.springdata;

import com.grubjack.cinema.dao.entities.User;
import com.grubjack.cinema.dao.interfcs.IUserDao;
import com.grubjack.cinema.dao.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;


public class UserDaoImpl implements IUserDao {
    private final UserRepository userRepository;

    User findByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }
}

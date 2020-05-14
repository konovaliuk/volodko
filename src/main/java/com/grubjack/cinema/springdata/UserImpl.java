package com.example.demo.springdata;

import com.example.demo.entities.User;
import com.example.demo.interfaces.IUser;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("UserService")
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class UserImpl  implements IUser {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Integer userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> findByNickname(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);

    }
}

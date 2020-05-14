package com.example.demo.springdata;

import com.example.demo.entities.PendingActivities;
import com.example.demo.entities.User;
import com.example.demo.interfaces.IPendingActivities;
import com.example.demo.interfaces.IUser;
import com.example.demo.repositories.PendingActivitiesRepository;
import com.example.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("PendingActivitiesService")
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class PendingActivitiesImpl implements IPendingActivities {
    @Autowired
    private PendingActivitiesRepository pendingActivitiesRepository;

    @Override
    public Iterable<PendingActivities> findAll() {
        return pendingActivitiesRepository.findAll();
    }

    @Override
    public Optional<PendingActivities> findById(Integer userId) {
        return pendingActivitiesRepository.findById(userId);
    }

    @Override
    public List<PendingActivities> findByActivitie(String nickname) {
        return pendingActivitiesRepository.findByActivitie(nickname);
    }

    @Override
    public void save(PendingActivities user) {
        pendingActivitiesRepository.save(user);

    }
}

package com.example.demo.interfaces;

import com.example.demo.entities.PendingActivities;
import com.example.demo.entities.User;

import java.util.List;
import java.util.Optional;

public interface IPendingActivities {
    Iterable<PendingActivities> findAll();

    Optional<PendingActivities> findById(Integer activitieId);

    List<PendingActivities> findByActivitie(String activitie);

    void save(PendingActivities pendingActivities);
}

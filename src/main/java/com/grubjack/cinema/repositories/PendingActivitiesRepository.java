package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.PendingActivities;
import org.springframework.data.repository.CrudRepository;

public interface PendingActivitiesRepository extends CrudRepository<PendingActivities, Integer>{

    List<PendingActivities> findByActivitie(String nickname);
}

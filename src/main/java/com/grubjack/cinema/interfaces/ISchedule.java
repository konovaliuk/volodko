package com.example.demo.interfaces;

import com.example.demo.entities.User;
import com.example.demo.entities.Schedule;

import java.util.List;
import java.util.Optional;

public interface ISchedule {
    List<Schedule> findAll();

    Optional<Schedule> findById(Integer scheduleId);


    void save(Schedule schedule);
}

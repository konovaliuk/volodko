package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import com.example.demo.entities.Schedule;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
}

package com.example.demo.springdata;

import com.example.demo.entities.PendingActivities;
import com.example.demo.entities.Schedule;
import com.example.demo.interfaces.ISchedule;
import com.example.demo.repositories.PendingActivitiesRepository;
import com.example.demo.repositories.ScheduleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository("ScheduleService")
@Transactional
//@RequiredArgsConstructor
@NoArgsConstructor
public class ScheduleImpl implements ISchedule {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> findAll() {
        return (List<Schedule>) scheduleRepository.findAll();
    }

    @Override
    public Optional<Schedule> findById(Integer scheduleId) {
        return scheduleRepository.findById(scheduleId);
    }

//    @Override
//    public List<Schedule> findByActivitie(String nickname) {
//        return pendingActivitiesRepository.findByActivitie(nickname);
//    }

    @Override
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);

    }
}

package com.example.demo.services;

import com.example.demo.entities.Schedule;
import com.example.demo.interfaces.ISchedule;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class TimeTrackingService {

    //@Autowired
    private final ISchedule iSchedule;

    @Transactional(readOnly = true)
    public List<Schedule> getSchedule(){
        return iSchedule.findAll();
    }


}

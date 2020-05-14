package com.grubjack.cinema.service;

import com.grubjack.cinema.exception.DaoException;
import com.grubjack.cinema.model.DayOfWeek;
import com.grubjack.cinema.model.Show;
import com.grubjack.cinema.model.TimeOfDay;

import java.util.List;
import java.util.Map;

public interface ShowService {

    List<Show> findAll();
    void delete(int showId);
    void create(Show show);
    int getAttendance(int showId;
    public Map<TimeOfDay, Map<DayOfWeek, Show>> getSchedule();
}

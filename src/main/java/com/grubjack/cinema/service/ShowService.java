package com.grubjack.cinema.service;

import com.grubjack.cinema.exception.DaoException;
import com.grubjack.cinema.model.DayOfWeek;
import com.grubjack.cinema.model.Show;
import com.grubjack.cinema.model.TimeOfDay;

import java.util.List;
import java.util.Map;

/**
 * {@code ShowService} interface for service working with logic of movies
 */
public interface ShowService {

    List<Show> findAll() throws DaoException;
    void delete(int showId) throws DaoException;
    void create(Show show) throws DaoException;
    int getAttendance(int showId) throws DaoException;
    public Map<TimeOfDay, Map<DayOfWeek, Show>> getSchedule() throws DaoException;
}

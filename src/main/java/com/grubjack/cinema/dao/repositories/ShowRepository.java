package com.grubjack.cinema.dao.repositories;

import com.grubjack.cinema.dao.entities.Show;
import com.grubjack.cinema.dao.enums.DayOfWeek;
import com.grubjack.cinema.dao.enums.TimeOfDay;

import java.util.List;

public interface ShowRepository extends CrudRepository<Show, Integer>{
    List<Show> findByDay(DayOfWeek dayOfWeek);
    List<Show> findByTime(TimeOfDay timeOfDay);
    Show findByDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay);
    List<Show> findByMovie(String movie);
    Show findByTicket(int ticketId);
}

package com.grubjack.cinema.dao.interfcs;

import com.grubjack.cinema.dao.entities.Show;
import com.grubjack.cinema.dao.enums.TimeOfDay;
import com.grubjack.cinema.dao.enums.DayOfWeek;
import java.util.List;

public interface IShowDao {
    List<Show> findByDay(DayOfWeek dayOfWeek);
    List<Show> findByTime(TimeOfDay timeOfDay);
    Show findByDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay);
    List<Show> findByMovie(String movie);
    Show findByTicket(int ticketId);
}

package com.grubjack.cinema.dao.springdata;

import com.grubjack.cinema.dao.entities.Show;
import com.grubjack.cinema.dao.enums.DayOfWeek;
import com.grubjack.cinema.dao.enums.TimeOfDay;
import com.grubjack.cinema.dao.interfcs.IShowDao;
import com.grubjack.cinema.dao.repositories.ShowRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository("ShowService")
@Transactional
@AllArgsConstructor
public class ShowDaoImpl implements IShowDao {
    private final ShowRepository showRepository;

    public List<Show> findByDay(DayOfWeek dayOfWeek)
    {
        return showRepository.findByDay(dayOfWeek);
    }
    public List<Show> findByTime(TimeOfDay timeOfDay)
    {
        return showRepository.findByTime(timeOfDay);
    }
    public Show findByDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay)
    {
        return showRepository.findByDayAndTime(dayOfWeek, timeOfDay);
    }
    public List<Show> findByMovie(String movie)
    {
        return showRepository.findByMovie(movie);
    }
    public Show findByTicket(int ticketId)
    {
        return showRepository.findByTicket(ticketId);
    }
}

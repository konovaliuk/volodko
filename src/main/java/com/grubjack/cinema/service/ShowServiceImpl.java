package com.grubjack.cinema.service;

import com.grubjack.cinema.dao.interfcs.IShowDao;
import com.grubjack.cinema.dao.interfcs.ITicketDao;
import com.grubjack.cinema.exception.DaoException;
import com.grubjack.cinema.model.DayOfWeek;
import com.grubjack.cinema.model.Show;
import com.grubjack.cinema.model.Ticket;
import com.grubjack.cinema.model.TimeOfDay;
import com.grubjack.cinema.util.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class ShowServiceImpl implements ShowService {

    private static Logger log = LoggerFactory.getLogger(ShowServiceImpl.class);
    private IShowDao showDao;
    private ITicketDao ticketDao;

    public ShowServiceImpl(IShowDao showDao, ITicketDao ticketDao) {
        this.showDao = showDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public List<Show> findAll() throws DaoException {
        log.info("Get all show");
        return showDao.findAll();
    }

    @Override
    public Map<TimeOfDay, Map<DayOfWeek, Show>> getSchedule() throws DaoException {
        log.info("Get schedule");
        return showDao.findAll().stream().collect(groupingBy(Show::getTimeOfDay, toMap(Show::getDayOfWeek, identity())));
    }

    @Override
    public void delete(int showId) throws DaoException {
        log.info("Delete show with id {}", showId);
        showDao.delete(showId);
    }

    public Show findByTicket(int ticketId) throws DaoException {
        log.info("Get show by ticket with id {}", ticketId);
        return showDao.findByTicket(ticketId);
    }

    @Override
    public void create(Show show) throws DaoException {
        log.info("Create show {}", show.toString());
        String totalRows = ConfigManager.getInstance().getProperty(ConfigManager.HALL_ROW_VALUE);
        String seatsPerRow = ConfigManager.getInstance().getProperty(ConfigManager.HALL_SEAT_VALUE);

        log.info("Create tickets for new show");
        for (int line = 1; line <= Integer.parseInt(totalRows); line++) {
            for (int seat = 1; seat <= Integer.parseInt(seatsPerRow); seat++) {
                show.getTickets().add(new Ticket(line, seat, computeCost(show.getTimeOfDay())));
            }
        }
        showDao.create(show);
    }

    @Override
    public int getAttendance(int showId) throws DaoException {
        log.info("Compute attendance for show with id {}", showId);
        List<Ticket> showTickets = ticketDao.findByShow(showId);
        return showTickets.size() > 0 ? (int) (showTickets.stream().filter(Ticket::isSold).count() * 100 / showTickets.size()) : 0;
    }

    private int computeCost(TimeOfDay timeOfDay) {
        int result = 0;
        switch (timeOfDay) {
            case FIRST:
            case SECOND:
                result = Integer.parseInt(ConfigManager.getInstance().getProperty(ConfigManager.TICKET_LOW_PRICE));
                break;
            case THIRD:
            case FOURTH:
                result = Integer.parseInt(ConfigManager.getInstance().getProperty(ConfigManager.TICKET_MIDDLE_PRICE));
                break;
            case FIFTH:
            case SIXTH:
                result = Integer.parseInt(ConfigManager.getInstance().getProperty(ConfigManager.TICKET_HIGH_PRICE));
                break;
        }
        return result;
    }
}

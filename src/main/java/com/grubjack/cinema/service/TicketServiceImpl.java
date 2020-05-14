package com.grubjack.cinema.service;

import com.grubjack.cinema.dao.interfcs.IShowDao;
import com.grubjack.cinema.dao.interfcs.ITicketDao;
import com.grubjack.cinema.exception.DaoException;
import com.grubjack.cinema.model.Show;
import com.grubjack.cinema.model.Ticket;
import com.grubjack.cinema.to.TicketWithShow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class TicketServiceImpl implements TicketService {

    private static Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);
    private ITicketDao ticketDao;
    private IShowDao showDao;

    public TicketServiceImpl(ITicketDao ticketDao, IShowDao showDao) {
        this.ticketDao = ticketDao;
        this.showDao = showDao;
    }
    @Override
    public List<TicketWithShow> findByUser(int userId) throws DaoException {
        log.info("Get tickets of user with id {}", userId);
        List<TicketWithShow> ticketWithShows = new ArrayList<>();
        for (Ticket ticket : ticketDao.findByUser(userId)) {
            log.info("Get show of ticket with id {}", ticket.getId());
            Show show = showDao.findByTicket(ticket.getId());
            ticketWithShows.add(new TicketWithShow(ticket.getId(), ticket.getLine(), ticket.getSeat(), ticket.getPrice(), show.getDayOfWeek(), show.getTimeOfDay(), show.getMovieName()));
        }
        return ticketWithShows;
    }

    @Override
    public Map<Integer, Map<Integer, Ticket>> findByShowGroupByPlace(int showId) throws DaoException {
        log.info("Get ticket of show with id {} group by place", showId);
        return ticketDao.findByShow(showId).stream().collect(groupingBy(Ticket::getLine, toMap(Ticket::getSeat, identity())));
    }

    @Override
    public void cancel(int id) throws DaoException {
        log.info("Cancel ticket with id {}", id);
        ticketDao.cancel(id);
    }

    @Override
    public void buyTicket(int id, int userId) throws DaoException {
        log.info("Buy ticket with id {} by user with id {}", id, userId);
        ticketDao.buyTicket(id, userId);
    }

}

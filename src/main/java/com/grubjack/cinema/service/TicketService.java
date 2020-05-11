package com.grubjack.cinema.service;

import com.grubjack.cinema.exception.DaoException;
import com.grubjack.cinema.model.Ticket;
import com.grubjack.cinema.to.TicketWithShow;

import java.util.List;
import java.util.Map;

public interface TicketService {
    List<TicketWithShow> findByUser(int userId) throws DaoException;

    Map<Integer, Map<Integer, Ticket>> findByShowGroupByPlace(int showId) throws DaoException;

    void buyTicket(int id, int userId) throws DaoException;

    void cancel(int id) throws DaoException;

}

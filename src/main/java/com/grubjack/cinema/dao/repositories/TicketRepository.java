package com.grubjack.cinema.dao.repositories;

import com.grubjack.cinema.dao.entities.Ticket;

import java.util.List;

public interface TicketRepository {
    void save(Ticket ticket, int showId);
    void delete(int id);
    Ticket find(int id);
    List<Ticket> findAll();
    List<Ticket> findByUser(int userId);
    List<Ticket> findByShow(int showId);
    List<Ticket> findBySold(boolean sold);
    void addTicket(int id, int userId);
    void cancel(int id);
}

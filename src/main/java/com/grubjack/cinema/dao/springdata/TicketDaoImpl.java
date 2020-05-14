package com.grubjack.cinema.dao.springdata;

import com.grubjack.cinema.dao.entities.Ticket;
import com.grubjack.cinema.dao.interfcs.ITicketDao;
import com.grubjack.cinema.dao.repositories.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TicketDaoImpl implements ITicketDao {
    private final TicketRepository ticketRepository;
    public void save(Ticket ticket, int showId)
    {
        ticketRepository.save(ticket, showId);
    }
    public void delete(int id)
    {
        ticketRepository.delete(id);
    }
    public Ticket find(int id)
    {
        return ticketRepository.find(id);
    }
    public List<Ticket> findAll()
    {
        return ticketRepository.findAll();
    }
    public List<Ticket> findByUser(int userId)
    {
        return ticketRepository.findByUser(userId);
    }
    public List<Ticket> findByShow(int showId)
    {
        return ticketRepository.findByShow(showId);
    }
    public List<Ticket> findBySold(boolean sold)
    {
        return ticketRepository.findBySold(sold);
    }
    public void addTicket(int id, int userId)
    {
        ticketRepository.addTicket(id, userId);
    }
    public void cancel(int id)
    {
        ticketRepository.delete(id);
    }
}

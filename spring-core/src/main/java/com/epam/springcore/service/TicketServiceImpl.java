package com.epam.springcore.service;

import com.epam.springcore.model.Event;
import com.epam.springcore.model.Ticket;
import com.epam.springcore.model.Ticket.Category;
import com.epam.springcore.model.User;
import com.epam.springcore.repository.TicketRepository;
import java.util.List;

public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;

  public TicketServiceImpl(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  @Override
  public Ticket bookTicket(long userId, long eventId, int place, Category category) {
    return ticketRepository.createTicket(userId, eventId, place, category);
  }

  @Override
  public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
    return ticketRepository.findBookedTickets(user, pageSize, pageNum);
  }

  @Override
  public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
    return ticketRepository.findBookedTickets(event, pageSize, pageNum);
  }

  @Override
  public boolean cancelTicket(long ticketId) {
    return ticketRepository.deleteTicket(ticketId);
  }
}

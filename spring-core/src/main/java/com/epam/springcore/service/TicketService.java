package com.epam.springcore.service;

import com.epam.springcore.model.Event;
import com.epam.springcore.model.Ticket;
import com.epam.springcore.model.User;
import java.util.List;

public interface TicketService {

  Ticket bookTicket(long userId, long eventId, int place, Ticket.Category category);

  List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

  List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

  boolean cancelTicket(long ticketId);
}

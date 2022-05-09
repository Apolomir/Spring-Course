package com.epam.springcore.repository;

import com.epam.springcore.model.Event;
import com.epam.springcore.model.Ticket;
import com.epam.springcore.model.User;
import com.epam.springcore.repository.storage.Storage;
import java.util.List;

public interface TicketRepository {

  void setTicketStorage(Storage<Ticket> ticketStorage);

  Ticket createTicket(long userId, long eventId, int place, Ticket.Category category);

  List<Ticket> findBookedTickets(User user, int pageSize, int pageNum);

  List<Ticket> findBookedTickets(Event event, int pageSize, int pageNum);

  boolean deleteTicket(long ticketId);
}

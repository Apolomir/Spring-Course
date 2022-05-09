package com.epam.springcore.repository;

import com.epam.springcore.model.Event;
import com.epam.springcore.model.Ticket;
import com.epam.springcore.model.Ticket.Category;
import com.epam.springcore.model.User;
import com.epam.springcore.repository.storage.Storage;
import com.epam.springcore.utils.PaginationUtils;
import java.util.List;
import java.util.stream.Collectors;

public class TicketRepositoryImpl implements TicketRepository {

  private Storage<Ticket> ticketStorage;

  @Override
  public void setTicketStorage(Storage<Ticket> ticketStorage) {
    this.ticketStorage = ticketStorage;
  }

  @Override
  public Ticket createTicket(long userId, long eventId, int place, Category category) {
    long ticketId = ticketStorage.getStorage().keySet().stream().max(Long::compareTo).orElse(0L);
    Ticket ticket = new Ticket(++ticketId, eventId, userId, category, place);
    ticketStorage.getStorage().put(ticketId, ticket);
    return ticket;
  }

  @Override
  public List<Ticket> findBookedTickets(User user, int pageSize, int pageNum) {
    final List<Ticket> tickets =
        ticketStorage.getStorage().values().stream()
            .filter(ticket -> user.getId() == ticket.getUserId())
            .collect(Collectors.toList());
    return PaginationUtils.paginateList(tickets, pageSize, pageNum);
  }

  @Override
  public List<Ticket> findBookedTickets(Event event, int pageSize, int pageNum) {
    final List<Ticket> tickets =
        ticketStorage.getStorage().values().stream()
            .filter(ticket -> event.getId() == ticket.getEventId())
            .collect(Collectors.toList());
    return PaginationUtils.paginateList(tickets, pageSize, pageNum);
  }

  @Override
  public boolean deleteTicket(long ticketId) {
    return null != ticketStorage.getStorage().remove(ticketId);
  }
}

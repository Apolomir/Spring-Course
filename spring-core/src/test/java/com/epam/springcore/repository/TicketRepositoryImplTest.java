package com.epam.springcore.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.epam.springcore.model.Event;
import com.epam.springcore.model.Ticket;
import com.epam.springcore.model.Ticket.Category;
import com.epam.springcore.model.User;
import com.epam.springcore.repository.storage.Storage;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TicketRepositoryImplTest {

  static final int TEST_TICKET_ID = 1;
  static final int TEST_TICKET_USER_ID = 1;
  static final int TEST_TICKET_EVENT_ID = 1;
  static final Category TEST_TICKET_CATEGORY = Category.PREMIUM;
  static final int TEST_TICKET_PLACE = 1;

  TicketRepository ticketRepository;

  @Mock Storage<Ticket> ticketStorage;

  Ticket testTicket;

  @BeforeEach
  void init() {
    this.ticketRepository = new TicketRepositoryImpl();
    ticketRepository.setTicketStorage(ticketStorage);
    Map<Long, Ticket> ticketMap = new HashMap<>();
    this.testTicket =
        Ticket.builder()
            .id(TEST_TICKET_ID)
            .userId(TEST_TICKET_USER_ID)
            .eventId(TEST_TICKET_EVENT_ID)
            .category(TEST_TICKET_CATEGORY)
            .place(TEST_TICKET_PLACE)
            .build();
    ticketMap.put(testTicket.getId(), testTicket);
    when(ticketStorage.getStorage()).thenReturn(ticketMap);
  }

  @Test
  void createTicket_TicketIsCreated() {
    assertThat(ticketRepository.createTicket(2, 2, 2, Category.BAR).getId()).isPositive();
  }

  @Test
  void findBookedTickets_UserTicketsAreFound() {
    User testUser = User.builder().id(1).build();
    assertThat(ticketRepository.findBookedTickets(testUser, 1, 1)).isNotEmpty();
  }

  @Test
  void findBookedTickets_EventTicketsAreFound() {
    Event testEvent = Event.builder().id(1).build();
    assertThat(ticketRepository.findBookedTickets(testEvent, 1, 1)).isNotEmpty();
  }

  @Test
  void deleteTicket_TicketIsDeleted() {
    assertTrue(ticketRepository.deleteTicket(TEST_TICKET_ID));
  }
}

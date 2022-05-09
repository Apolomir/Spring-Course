package com.epam.springcore.facade;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.epam.springcore.model.Event;
import com.epam.springcore.model.Ticket.Category;
import com.epam.springcore.model.User;
import java.util.Date;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration("/application-context.xml")
class BookingFacadeImplIntegrationTest {

  BookingFacadeImpl bookingFacade;

  @Autowired
  void setBookingFacade(BookingFacadeImpl bookingFacade) {
    this.bookingFacade = bookingFacade;
  }

  @Test
  @Order(1)
  void createUser_UserIsCreated() {
    User newTestUser = User.builder().name("Dima").email("test@mail.com").build();
    assertNotNull(bookingFacade.createUser(newTestUser));
  }

  @Test
  @Order(2)
  void createEvent_EventIsCreated() {
    Event newTestEvent = Event.builder().date(new Date()).title("New Test Event").build();
    assertNotNull(bookingFacade.createEvent(newTestEvent));
  }

  @Test
  @Order(3)
  void createTicket_TicketIsCreated() {
    assertNotNull(bookingFacade.bookTicket(1, 1, 20, Category.STANDARD));
  }
}

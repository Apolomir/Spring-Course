package com.epam.springcore;

import com.epam.springcore.facade.BookingFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
        "application-context.xml")) {
      LOGGER.debug("Context Initialized");
      BookingFacade bookingFacade = (BookingFacade) context.getBean("bookingFacade");
      LOGGER.debug("Test user: {}", bookingFacade.getUserById(1));
    }
  }
}

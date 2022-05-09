package com.epam.springcore.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.epam.springcore.model.Event;
import com.epam.springcore.repository.storage.Storage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EventRepositoryImplTest {

  static final int TEST_EVENT_ID = 1;
  static final String TEST_EVENT_TITLE = "Test Event";
  static final Date TEST_EVENT_DATE = new Date();

  EventRepository eventRepository;

  @Mock Storage<Event> eventStorage;
  Event testEvent;

  @BeforeEach
  void init() {
    eventRepository = new EventRepositoryImpl();
    eventRepository.setEventStorage(eventStorage);
    testEvent =
        Event.builder().id(TEST_EVENT_ID).title(TEST_EVENT_TITLE).date(TEST_EVENT_DATE).build();
    Map<Long, Event> testMap = new HashMap<>();
    testMap.put(testEvent.getId(), testEvent);
    Mockito.when(eventStorage.getStorage()).thenReturn(testMap);
  }

  @Test
  void findEventById_EventIsFound() {
    assertNotNull(eventRepository.findEventById(TEST_EVENT_ID));
  }

  @Test
  void findEventsByTitle_EventIsFound() {
    assertThat(eventRepository.findEventsByTitle(TEST_EVENT_TITLE, 1, 1)).isNotEmpty();
  }

  @Test
  void findEventsByDay_EventIsFound() {
    assertThat(eventRepository.findEventsByDay(TEST_EVENT_DATE, 1, 1)).isNotEmpty();
  }

  @Test
  void createEvent_EventIsCreated() {
    Event newTestEvent = Event.builder().title("New Test Event").date(new Date()).build();
    assertThat(eventRepository.createEvent(newTestEvent).getId()).isPositive();
  }

  @Test
  void updateEvent_EventIsUpdated() {
    testEvent.setTitle("Updated Event Title");
    assertEquals(testEvent, eventRepository.updateEvent(testEvent));
  }

  @Test
  void deleteEvent_EventIsDeleted() {
    assertTrue(eventRepository.deleteEvent(TEST_EVENT_ID));
  }
}

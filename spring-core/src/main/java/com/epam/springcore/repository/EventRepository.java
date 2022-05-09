package com.epam.springcore.repository;

import com.epam.springcore.model.Event;
import com.epam.springcore.repository.storage.Storage;
import java.util.Date;
import java.util.List;

public interface EventRepository {

  void setEventStorage(Storage<Event> eventStorage);

  Event findEventById(long id);

  List<Event> findEventsByTitle(String title, int pageSize, int pageNum);

  List<Event> findEventsByDay(Date day, int pageSize, int pageNum);

  Event createEvent(Event event);

  Event updateEvent(Event event);

  boolean deleteEvent(long eventId);
}

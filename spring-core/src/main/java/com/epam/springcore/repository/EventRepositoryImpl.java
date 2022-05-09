package com.epam.springcore.repository;

import com.epam.springcore.model.Event;
import com.epam.springcore.repository.storage.Storage;
import com.epam.springcore.utils.PaginationUtils;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventRepositoryImpl implements EventRepository {

  private Storage<Event> eventStorage;

  @Override
  public void setEventStorage(Storage<Event> eventStorage) {
    this.eventStorage = eventStorage;
  }

  @Override
  public Event findEventById(long id) {
    return eventStorage.getStorage().get(id);
  }

  @Override
  public List<Event> findEventsByTitle(String title, int pageSize, int pageNum) {
    final List<Event> events =
        eventStorage.getStorage().values().stream()
            .filter(event -> event.getTitle().contains(title))
            .collect(Collectors.toList());
    return PaginationUtils.paginateList(events, pageSize, pageNum);
  }

  @Override
  public List<Event> findEventsByDay(Date day, int pageSize, int pageNum) {
    final List<Event> eventList =
        eventStorage.getStorage().values().stream()
            .filter(event -> event.getDate().equals(day))
            .collect(Collectors.toList());
    return PaginationUtils.paginateList(eventList, pageSize, pageNum);
  }

  @Override
  public Event createEvent(Event event) {
    long eventId = eventStorage.getStorage().keySet().stream().max(Long::compareTo).orElse(0L);
    event.setId(++eventId);
    eventStorage.getStorage().put(eventId, event);
    return event;
  }

  @Override
  public Event updateEvent(Event event) {
    eventStorage.getStorage().put(event.getId(), event);
    return eventStorage.getStorage().get(event.getId());
  }

  @Override
  public boolean deleteEvent(long eventId) {
    return null != eventStorage.getStorage().remove(eventId);
  }
}

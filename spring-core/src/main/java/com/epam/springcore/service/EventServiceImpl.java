package com.epam.springcore.service;

import com.epam.springcore.model.Event;
import com.epam.springcore.repository.EventRepository;
import java.util.Date;
import java.util.List;

public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;

  public EventServiceImpl(EventRepository eventRepository) {
    this.eventRepository = eventRepository;
  }

  @Override
  public Event getEventById(long eventId) {
    return eventRepository.findEventById(eventId);
  }

  @Override
  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    return eventRepository.findEventsByTitle(title, pageSize, pageNum);
  }

  @Override
  public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
    return eventRepository.findEventsByDay(day, pageSize, pageNum);
  }

  @Override
  public Event createEvent(Event event) {
    return eventRepository.createEvent(event);
  }

  @Override
  public Event updateEvent(Event event) {
    return eventRepository.updateEvent(event);
  }

  @Override
  public boolean deleteEvent(long eventId) {
    return eventRepository.deleteEvent(eventId);
  }
}

package backend.project.services;

import backend.project.dtos.EventDTO;
import backend.project.entities.Event;

import java.util.List;

public interface EventService {

    public Event createEvent(EventDTO eventDTO);
    public Event updateEvent(Long id, Event updatedEvent);
    public void deleteEvent(Long id);
    public Event getEventById(Long id);
    public List<Event> getAllEvents();

}

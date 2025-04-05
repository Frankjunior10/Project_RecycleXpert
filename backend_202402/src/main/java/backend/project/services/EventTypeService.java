package backend.project.services;

import backend.project.dtos.EventTypeDTO;
import backend.project.dtos.VolunteerDTO;
import backend.project.entities.Event;
import backend.project.entities.EventType;
import backend.project.entities.Volunteer;

import java.util.List;

public interface EventTypeService {

    public EventType addEventType(EventType eventType);
    public List<EventType> getAllEventTypes();
    public EventType getEventTypeById(Long id);

}

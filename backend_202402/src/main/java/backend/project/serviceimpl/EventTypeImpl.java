package backend.project.serviceimpl;

import backend.project.dtos.EventTypeDTO;
import backend.project.dtos.VolunteerDTO;
import backend.project.entities.EventType;
import backend.project.entities.Volunteer;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.EventTypeRepository;
import backend.project.services.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTypeImpl implements EventTypeService {
    @Autowired
    EventTypeRepository eventTypeRepository;

    @Override
    public EventType addEventType(EventType eventType) {
        return eventTypeRepository.save(eventType);

    }

    @Override
    public List<EventType> getAllEventTypes() {
        return eventTypeRepository.findAll();
    }

    @Override
    public EventType getEventTypeById(Long id) {
        EventType eventTypeFound = eventTypeRepository.findById(id).orElse(null);
        if(eventTypeFound == null){
            throw new ResourceNotFoundException("Volunteer with id: " + id + " not found");
        }
        return eventTypeFound;
    }


}

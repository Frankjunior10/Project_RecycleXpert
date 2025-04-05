package backend.project.serviceimpl;

import backend.project.dtos.EventDTO;
import backend.project.entities.Event;
import backend.project.entities.EventType;
import backend.project.entities.Organization;
import backend.project.exceptions.KeyRepeatedDataException;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.EventRepository;
import backend.project.repositories.EventTypeRepository;
import backend.project.repositories.OrganizationRepository;
import backend.project.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    EventTypeRepository eventTypeRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    /*
    public Long eventIdByName(String name) {
        List<Event>listNombreDuplicados=eventRepository.findByName(name);
        if(!listNombreDuplicados.isEmpty()){
            return listNombreDuplicados.get(0).getId();
        }
        return 0L;
    }
       */

    // Crear un nuevo evento
    public Event createEvent(EventDTO eventDTO) {

        Organization organization = organizationRepository.findById(eventDTO.getOrganizationId()).orElse(null);
        EventType eventType = eventTypeRepository.findById(eventDTO.getEventTypeId()).orElse(null);
        Event event = new Event(0L, eventDTO.getEventName(), eventDTO.getDescription(),
                eventDTO.getLocation(), eventDTO.getCapacity(), eventDTO.getDate());

        event.setEventType(eventType);
        event.setOrganization(organization);

        return eventRepository.save(event);
    }

    // Modificar un evento existente
    public Event updateEvent(Long id, Event updatedEvent) {
        Optional<Event> existingEventOpt = eventRepository.findById(id);
        if (existingEventOpt.isPresent()) {
            Event existingEvent = existingEventOpt.get();
            existingEvent.setEventName(updatedEvent.getEventName());
            existingEvent.setDescription(updatedEvent.getDescription());
            existingEvent.setDate(updatedEvent.getDate());
            existingEvent.setLocation(updatedEvent.getLocation());
            existingEvent.setCapacity(updatedEvent.getCapacity());
            existingEvent.setOrganization(updatedEvent.getOrganization());
            //existingEvent.setEventType(updatedEvent.getEventType());
            // Si es necesario, se actualiza las relaciones como registerEvents, questionEvents y donations
            return eventRepository.save(existingEvent);
        } else {
            throw new RuntimeException("Event with id " + id + " not found");
        }
    }

    // Eliminar un evento
    public void deleteEvent(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        } else {
            throw new RuntimeException("Event with id " + id + " not found");
        }
    }

    // Obtener un evento por su ID
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event with id " + id + " not found"));
    }

    // Obtener todos los eventos
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}

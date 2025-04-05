package backend.project.serviceimpl;

import backend.project.dtos.RegisterEventDTO;
import backend.project.entities.Event;
import backend.project.entities.RegisterEvent;
import backend.project.entities.Volunteer;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.EventRepository;
import backend.project.repositories.RegisterEventRepository;
import backend.project.repositories.VolunteerRepository;
import backend.project.services.RegisterEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterEventServiceImpl implements RegisterEventService {
    @Autowired
    private RegisterEventRepository registerEventRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    /*

    // Agregar inscripción de un voluntario a un evento
    public RegisterEvent registerVolunteerToEvent(Long eventId, Long volunteerId) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        Optional<Volunteer> volunteerOpt = volunteerRepository.findById(volunteerId);

        if (eventOpt.isPresent() && volunteerOpt.isPresent()) {
            Event event = eventOpt.get();
            Volunteer volunteer = volunteerOpt.get();

            RegisterEvent registerEvent = new RegisterEvent();
            registerEvent.setEvent(event);
            registerEvent.setVolunteer(volunteer);
            registerEvent.setAttendance(false); // Por defecto no ha asistido

            return registerEventRepository.save(registerEvent);
        } else {
            throw new ResourceNotFoundException("Event or Volunteer not found");
        }
    }

     */

    // Eliminar inscripción de un voluntario de un evento
    public void unregisterVolunteerFromEvent(Long eventId, Long volunteerId) {
        Optional<RegisterEvent> registerEventOpt = registerEventRepository.findRegisterEventByEvent_IdAndVolunteerId(eventId, volunteerId);

        if (registerEventOpt.isPresent()) {
            registerEventRepository.delete(registerEventOpt.get());
        } else {
            throw new ResourceNotFoundException("No registration found for the given event and volunteer");
        }
    }

    @Override
    public RegisterEvent createRegisterEvent(RegisterEventDTO registerEventDTO) {

        Volunteer volunteer = volunteerRepository.findById(registerEventDTO.getVolunteerId()).orElse(null);
        Event event = eventRepository.findById(registerEventDTO.getEventId()).orElse(null);

        RegisterEvent registerEvent = new RegisterEvent();

        registerEvent.setVolunteer(volunteer);
        registerEvent.setEvent(event);

        return registerEventRepository.save(registerEvent);

    }

    /*
    private Long id;
    private boolean attendance;

    private Long eventId;  // Relation con Event
    private Long volunteerId;  // Relation con Volunteer
     */



    /*


    @Override
    public RegisterEvent createRegisterEvent(RegisterEventDTO registerEventDTO) {
        // Obtener el voluntario
        Volunteer volunteer = volunteerRepository.findById(registerEventDTO.getVolunteerId())
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found with id: " + registerEventDTO.getVolunteerId()));
        System.out.println("Volunteer loaded: " + volunteer);

        // Obtener el evento
        Event event = eventRepository.findById(registerEventDTO.getEventId())
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with id: " + registerEventDTO.getEventId()));
        System.out.println("Event loaded: " + event);

        // Obtener los puntos del tipo de evento
        if (event.getEventType() == null) {
            throw new ResourceNotFoundException("Event type is missing for event id: " + event.getId());
        }
        int rewards = event.getEventType().getRewards();
        System.out.println("Rewards: " + rewards);

        // Asignar puntos y actualizar nivel
        volunteer.setPoints(volunteer.getPoints() + rewards);
        updateVolunteerLevel(volunteer);

        // Guardar voluntario
        volunteerRepository.save(volunteer);

        // Crear registro de evento
        RegisterEvent registerEvent = new RegisterEvent();
        registerEvent.setVolunteer(volunteer);
        registerEvent.setEvent(event);

        return registerEventRepository.save(registerEvent);
    }


    // Método para actualizar el nivel del voluntario basado en los puntos
    private void updateVolunteerLevel(Volunteer volunteer) {
        int points = volunteer.getPoints();
        int newLevel = calculateLevel(points);

        if (newLevel != volunteer.getLevel()) {
            volunteer.setLevel(newLevel);
        }
    }

    // Método que define el nivel basado en los puntos
    private int calculateLevel(int points) {
        if (points >= 1000) return 5;
        else if (points >= 500) return 4;
        else if (points >= 250) return 3;
        else if (points >= 100) return 2;
        else return 1;
    }
     */

}

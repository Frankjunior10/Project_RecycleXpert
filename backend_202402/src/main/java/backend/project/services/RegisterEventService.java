package backend.project.services;

import backend.project.dtos.EventDTO;
import backend.project.dtos.RegisterEventDTO;
import backend.project.entities.Event;
import backend.project.entities.RegisterEvent;

public interface RegisterEventService {
    //public RegisterEvent registerVolunteerToEvent(Long eventId, Long volunteerId);
    public void unregisterVolunteerFromEvent(Long eventId, Long volunteerId);

    public RegisterEvent createRegisterEvent(RegisterEventDTO registerEventDTO);

}

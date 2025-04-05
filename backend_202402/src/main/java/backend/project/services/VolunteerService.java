package backend.project.services;

import backend.project.dtos.VolunteerDTO;
import backend.project.dtos.VolunteerProgressDTO;
import backend.project.entities.Event;
import backend.project.entities.User;
import backend.project.entities.Volunteer;

import java.util.List;

public interface VolunteerService {

    public Volunteer findById(Long id);
    public List<Volunteer> listAllVolunteers();

    public Volunteer addVolunteer(VolunteerDTO volunteerdto);


    public void deleteVolunteer(Long id);
    public Volunteer updateVolunteer(Long id, VolunteerDTO volunteerdto);
    //public Volunteer updateVolunteer(Long id, Volunteer volunteer);

    public List<Event> getVolunteerRegisteredEvents(Long volunteerId);
    void addPointsToVolunteer(Long volunteerId, int points);
    VolunteerProgressDTO getVolunteerProgress(Long volunteerId);


}

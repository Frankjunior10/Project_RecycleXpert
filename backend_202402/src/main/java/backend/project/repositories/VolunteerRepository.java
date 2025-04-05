package backend.project.repositories;

import backend.project.entities.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, Long> {

    //Volunteer Ordenaars
    List<Volunteer> findAllByOrderByVolunteerNameAsc();
    //Guarda un Volunteer in the Base Dato
    Volunteer findByEmail(String email);
    public List<Volunteer> findByVolunteerName(String volunteerName);

    //void updatePoints(Long volunteerId, int points);
}

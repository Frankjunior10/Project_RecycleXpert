package backend.project.repositories;

import backend.project.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    //public List<Event> findByName(String name);

}

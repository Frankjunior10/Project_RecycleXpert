package backend.project.repositories;

import backend.project.entities.RegisterEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegisterEventRepository extends JpaRepository<RegisterEvent, Long> {
    Optional<RegisterEvent> findRegisterEventByEvent_IdAndVolunteerId(Long eventId, Long volunteerId);
}

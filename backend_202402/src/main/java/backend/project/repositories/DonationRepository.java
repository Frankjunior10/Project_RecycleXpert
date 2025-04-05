package backend.project.repositories;

import backend.project.entities.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findAllByEvent_Id(Long eventId);
}

package backend.project.repositories;

import backend.project.entities.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {


}

package backend.project.repositories;

import backend.project.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}

package backend.project.services;

import backend.project.dtos.OrganizationDTO;
import backend.project.entities.Organization;

import java.util.List;

public interface OrganizationService {

    public Organization createOrganization(Organization organization);
    public Organization updateOrganization(Long id, Organization updatedOrganization);
    public void deleteOrganization(Long id);
    public Organization getOrganizationById(Long id);
    public List<Organization> getAllOrganizations();
}

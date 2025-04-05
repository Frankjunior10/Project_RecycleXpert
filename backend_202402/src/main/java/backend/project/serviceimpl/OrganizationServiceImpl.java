package backend.project.serviceimpl;

import backend.project.dtos.OrganizationDTO;
import backend.project.entities.Organization;
import backend.project.repositories.OrganizationRepository;
import backend.project.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    // Crear una nueva organización
    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    // Modificar una organización existente
    public Organization updateOrganization(Long id, Organization updatedOrganization) {
        Optional<Organization> existingOrganizationOpt = organizationRepository.findById(id);
        if (existingOrganizationOpt.isPresent()) {
            Organization existingOrganization = existingOrganizationOpt.get();
            existingOrganization.setOrganizationName(updatedOrganization.getOrganizationName());
            existingOrganization.setOrgType(updatedOrganization.getOrgType());
            existingOrganization.setContactInfo(updatedOrganization.getContactInfo());
            existingOrganization.setCollaborationArea(updatedOrganization.getCollaborationArea());
            // Si es necesario, también se pueden actualizar las listas de eventos y consultas
            // existingOrganization.setEvents(updatedOrganization.getEvents());
            // existingOrganization.setConsultations(updatedOrganization.getConsultations());
            return organizationRepository.save(existingOrganization);
        } else {
            throw new RuntimeException("Organization with id " + id + " not found");
        }
    }

    // Eliminar una organización
    public void deleteOrganization(Long id) {
        if (organizationRepository.existsById(id)) {
            organizationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Organization with id " + id + " not found");
        }
    }

    // Obtener una organización por su ID
    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Organization with id " + id + " not found"));
    }

    // Obtener todas las organizaciones
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
}

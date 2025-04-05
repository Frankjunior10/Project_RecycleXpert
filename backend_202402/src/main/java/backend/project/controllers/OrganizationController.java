package backend.project.controllers;

import backend.project.entities.Organization;
import backend.project.serviceimpl.OrganizationServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class OrganizationController {
    @Autowired
    private OrganizationServiceImpl organizationService;

    // Obtener todas las organizaciones
    @GetMapping("/organizations")
    public ResponseEntity<List<Organization>> getAllOrganizations() {
        List<Organization> organizations = organizationService.getAllOrganizations();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    // Obtener una organización por su ID
    @GetMapping("/organizations/{id}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable Long id) {
        Organization organization = organizationService.getOrganizationById(id);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    // Crear una nueva organización
    @PostMapping("/organizations")
    public ResponseEntity<Organization> createOrganization(@RequestBody Organization organization) {
        Organization createdOrganization = organizationService.createOrganization(organization);
        return new ResponseEntity<>(createdOrganization, HttpStatus.CREATED);
    }

    // Modificar una organización existente
    @PutMapping("/organizations/{id}")
    public ResponseEntity<Organization> updateOrganization(@PathVariable Long id, @RequestBody Organization organization) {
        Organization updatedOrganization = organizationService.updateOrganization(id, organization);
        return new ResponseEntity<>(updatedOrganization, HttpStatus.OK);
    }

    // Eliminar una organización por su ID
    @DeleteMapping("/organization/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
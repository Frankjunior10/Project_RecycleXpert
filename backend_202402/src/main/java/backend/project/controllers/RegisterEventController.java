package backend.project.controllers;

import backend.project.dtos.EventDTO;
import backend.project.dtos.RegisterEventDTO;
import backend.project.entities.Event;
import backend.project.entities.RegisterEvent;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.serviceimpl.RegisterEventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class RegisterEventController {
    @Autowired
    private RegisterEventServiceImpl registerEventService;


    // Añadir un GET de mostrar


    // Registrar un voluntario en un evento
    @PostMapping("/registerevent")
    public ResponseEntity<RegisterEvent> createEvent(@RequestBody RegisterEventDTO registevent) {
        try {
            RegisterEvent newRegisterEvent = registerEventService.createRegisterEvent(registevent);
            return new ResponseEntity<>(newRegisterEvent, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @PostMapping("/registerevent")
    public ResponseEntity<RegisterEvent> registerVolunteerToEvent(
            @RequestParam Long eventId, @RequestParam Long volunteerId) {
        try {
            RegisterEvent registerEvent = registerEventService.registerVolunteerToEvent(eventId, volunteerId);
            return new ResponseEntity<>(registerEvent, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     */

    // Eliminar la inscripción de un voluntario en un evento
    @DeleteMapping("/registerevent")
    public ResponseEntity<Void> unregisterVolunteerFromEvent(
            @RequestParam Long eventId, @RequestParam Long volunteerId) {
        try {
            registerEventService.unregisterVolunteerFromEvent(eventId, volunteerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}

package backend.project.controllers;

import backend.project.dtos.EventDTO;
import backend.project.entities.Event;
import backend.project.serviceimpl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class EventController {
    @Autowired
    private EventServiceImpl eventService;

    // Obtener todos los eventos
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    // Obtener un evento por su ID
    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    // Crear un nuevo evento
    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody EventDTO event) {
        Event newEvent = eventService.createEvent(event);
        if(newEvent != null) {
            return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

 /*
    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        //Prueba
        Event newEvent = eventService.createEvent(event);
        if (newEvent != null) {
            return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

         Esto es el original
        // Aquí se pasa el Event directamente al servicio
        Event newEvent = eventService.createEvent(event);

        // Retornamos el nuevo evento creado con un status 201
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);


    }
     */

    // Modificar un evento existente
    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
        Event updatedEvent = eventService.updateEvent(id, event);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    // Eliminar un evento por su ID
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

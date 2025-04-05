package backend.project.controllers;

import backend.project.entities.EventType;
import backend.project.services.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class EventTypeController {
    @Autowired
    private EventTypeService eventTypeService;

    // Obtener todos los Tipos de Eventos
    @GetMapping("/eventypes")
    public ResponseEntity<List<EventType>> getAllEventTypess() {
        return new ResponseEntity<List<EventType>>(eventTypeService.getAllEventTypes(),HttpStatus.OK);
    }

    @GetMapping("/eventypes/{id}")
    public ResponseEntity<EventType> getEvenTypeById(@PathVariable Long id) {
        EventType eventypeFound = eventTypeService.getEventTypeById(id);
        if (eventypeFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(eventypeFound);
    }
}

package br.edu.ufop.web.ticket.sales.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufop.web.ticket.sales.dtos.CreateEventDTO;
import br.edu.ufop.web.ticket.sales.dtos.DeleteEventDTO;
import br.edu.ufop.web.ticket.sales.dtos.EventDTO;
import br.edu.ufop.web.ticket.sales.dtos.UpdateEventDTO;
import br.edu.ufop.web.ticket.sales.service.EventService;
import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("Event service is running");
    }

    @GetMapping("/events-list")
    public ResponseEntity<List<EventDTO>> getAllEvents() {

        List<EventDTO> eventList = eventService.getAllEvents();

        return ResponseEntity.ok(eventList);
    }
    
    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@RequestBody CreateEventDTO createEventDTO) {

        EventDTO eventDTO = eventService.createEvent(createEventDTO);
        return ResponseEntity.ok(eventDTO);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable(value = "eventId") String eventId) {

        EventDTO eventDTO = eventService.getEventById(eventId);

        if (eventDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(eventDTO);
    }

    @PutMapping
    public ResponseEntity<EventDTO> updateEvent(@RequestBody UpdateEventDTO updateEventDTO) {

        EventDTO eventDTO = eventService.updateEvent(updateEventDTO);

        if (eventDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(eventDTO);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Object> deleteEvent(@RequestBody DeleteEventDTO deleteEventDTO) {

        eventService.deleteEvent(deleteEventDTO);

        return ResponseEntity.ok("Event has been deleted.");

    }

    
    
    

}

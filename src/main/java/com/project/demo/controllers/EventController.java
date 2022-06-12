package com.project.demo.controllers;

import com.project.demo.entities.Event;
import com.project.demo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
@CrossOrigin
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/events")
    public ResponseEntity<List<Event>> getAllEvents(){
        List<Event> events = eventService.listAll();
        return new ResponseEntity(events, HttpStatus.OK);
    }

    @GetMapping(value = "/events/{eventId}")
    public ResponseEntity<Event> getEventById(@PathVariable(name = "eventId") Long id){
        Event event = eventService.findEventById(id);
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

    @PostMapping(value = "/events/add-event")
    public ResponseEntity<String> toAddEvent(@RequestBody Event event){
        eventService.addEvent(event);
        return new ResponseEntity<>("EVENT ADDED", HttpStatus.OK);
    }

    @PostMapping(value = "/events/{eventId}/update-event")
    public ResponseEntity<String> toUpdateEvent(@RequestBody Event event){
        eventService.updateEvent(event);
        return new ResponseEntity<>("EVENT UPDATED", HttpStatus.OK);
    }

    @PostMapping(value = "events/delete-event")
    public ResponseEntity<String> toDeleteEvent(@PathVariable(name = "eventId") Long id){
        eventService.deleteEvent(id);
        return new ResponseEntity<>("EVENT DELETED", HttpStatus.OK);
    }
}
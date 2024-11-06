package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController {
    //Write junits for all

    @Autowired
    EventService eventService;

    @GetMapping(value = "/{id}")
    //TODO: Modify this?
    public ResponseEntity<Optional<Event>> getEventById(@PathVariable Long id) {
        Optional<Event> event =  eventService.getEventById(id);
        if(!event.isPresent()) return  ResponseEntity.notFound().build();
        else return ResponseEntity.ok(event);

//       return eventService.getEventById(id).map(event1 -> ResponseEntity.ok(event1)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/total")
    //TODO: Implement this
    public ResponseEntity<Integer> getTotal(@RequestParam String by) throws Exception {
        Integer sum = 0;
        try {
             sum = eventService.total(by);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(sum);
    }

    @GetMapping(value = "/top3")
    //TODO: Implement this
    public ResponseEntity<List<Event>> getTop3(@RequestParam String by) throws Exception {
        List<Event> events = new ArrayList<>();
        try {
            events = eventService.top3(by);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(events);
    }


}
